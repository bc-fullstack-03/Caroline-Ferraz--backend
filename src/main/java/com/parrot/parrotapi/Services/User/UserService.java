package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IUserRepository;
import com.parrot.parrotapi.Domain.User;
import com.parrot.parrotapi.Services.FileUpload.IFileUploadService;
import com.parrot.parrotapi.Services.Post.IPostService;
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
    private IPostService _postService;

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

    public Page<GetUsersResponse> getUsers(Pageable pageable) {
        var response = _userRepository.findAll(pageable).map(GetUsersResponse::new);
        return response;
    }

    public void updateUser(UpdateUserRequest request) {
        var optionalUser = _userRepository.findById(request.getId());
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        user.updateUserData(request);
        _userRepository.save(user);
    }

    public void deleteUser(UUID id){
        _userRepository.deleteById(id);
    }

    public GetUserByIdResponse getUserById(UUID id){
        var optionalUser = _userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return new GetUserByIdResponse(user.getId(), user.getName(), user.getPhoto(), user.getFollowing(), user.getFollowers());
    }

    public Page<Post> getPostsByUser(UUID userId, Pageable pageable){
        return _postService.getPostsByUser(userId, pageable);
    }

    public void followOrUnfollowUser(UUID userId, FollowOrUnfollowUserRequest request){
        var optionalUser = _userRepository.findById(request.getId());
        User userFollowing = optionalUser.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        userFollowing.followOrUnfollowUser(userId);
        _userRepository.save(userFollowing);
    }

    public void addOrRemoveFollower(UUID userId, FollowOrUnfollowUserRequest request){
        var optionalUser = _userRepository.findById(userId);
        User userFollower = optionalUser.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        userFollower.addOrRemoveFollower(request.getId());
        _userRepository.save(userFollower);
    }

//    public FindUserResponse findUserByEmail(String email){
//        var user = _userRepository.findUserByEmail(email);
//        var response = new FindUserResponse(user.getId(), user.getName(), user.getPhoto(), user.getEmail());
//        return response;
//    }

    public User getUser(String email){
        return _userRepository.findUserByEmail(email);
    }

    public void uploadPhotoProfile(MultipartFile photoFile) throws Exception {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
}