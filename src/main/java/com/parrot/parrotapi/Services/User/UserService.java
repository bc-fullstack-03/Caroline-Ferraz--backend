package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IUserRepository;
import com.parrot.parrotapi.Domain.User;
import com.parrot.parrotapi.Services.FileUpload.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private IFileUploadService _fileUploadService;

    public String createUser(CreateUserRequest request) {

        if(_userRepository.findUserByEmail(request.email) != null){
            throw new RuntimeException("Este Usuário já existe.");
        } else {
            var hash = _passwordEncoder.encode(request.password);
            var user = new User(request.name, request.email, hash);

            _userRepository.save(user);

            return user.getId().toString();
        }
    }

    public Page<GetUsersResponse> getAllUsers(Pageable pageable) {
        var response = _userRepository.findAll(pageable).map(GetUsersResponse::new);
        return response;
    }

    public void updateUser(UpdateUserRequest request) {
        var user = this.getUserBySecurityContextHolder();
        if(_userRepository.findUserByEmail(request.email) != null){
            throw new RuntimeException("Este Usuário já existe.");
        } else {
            user.updateUserData(request);
            _userRepository.save(user);
        }
    }

    public void deleteUser(UUID id){
//        var user = this.getUserBySecurityContextHolder();
//        if(user.getId() != id){
//            throw new RuntimeException("Operação inválida.");
//        } else {
            _userRepository.deleteById(id);
//        }
    }

    public GetUserByIdResponse getUserById(UUID id){
        var user = this.getUserByUserId(id);
        return new GetUserByIdResponse(user.getId(), user.getName(), user.getPhoto(), user.getFollowing(), user.getFollowers());
    }

    public void followOrUnfollowUser(UUID userId, FollowOrUnfollowUserRequest request){
        var userFollowing = this.getUserByUserId(request.getId());
        userFollowing.followOrUnfollowUser(userId);
        _userRepository.save(userFollowing);
    }

    public void addOrRemoveFollower(UUID userId, FollowOrUnfollowUserRequest request){
        var userFollower = this.getUserByUserId(userId);
        userFollower.addOrRemoveFollower(request.getId());
        _userRepository.save(userFollower);
    }

    public User getUser(String email){
        return _userRepository.findUserByEmail(email);
    }

    public void uploadPhotoProfile(MultipartFile photoFile) throws Exception {
        var user = this.getUserBySecurityContextHolder();
        var photo = "";
        try{
            var fileName = user.getId() + "." + photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf(".") + 1);
            photo = _fileUploadService.upload(photoFile, fileName);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        user.setPhoto(photo);
        _userRepository.save(user);
    }

    public User getUserBySecurityContextHolder(){
        var id = ((GetUserByIdResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        var user = this.getUserByUserId(id);
        return user;
    }

    private User getUserByUserId(UUID id){
        var optionalUser = _userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return user;
    }
}