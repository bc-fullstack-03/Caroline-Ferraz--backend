package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Infrastructure.IUserRepository;
import com.parrot.parrotapi.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    public String createUser(CreateUserRequest request){
        var user = new User(request.name, request.email, request.password);
        _userRepository.save(user);

        return user.getId().toString();
    }
}
