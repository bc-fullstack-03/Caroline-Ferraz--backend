package com.parrot.parrotapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.parrot.parrotapi.Services.User.CreateUserRequest;
import com.parrot.parrotapi.Services.User.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request){
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//    public ResponseEntity<FindUserResponse> getUser(String email){
//
//    }
}
