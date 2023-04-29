package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Infrastructure.IUserRepository;
import com.parrot.parrotapi.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email, request.password);
        _userRepository.save(user);

        return user.getId().toString();
    }

    public List<GetUsersRequest> getUsers() {
        return _userRepository.findAll().stream().map(GetUsersRequest::new).toList();
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

}
