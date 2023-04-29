package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Services.User.GetUsersRequest;
import com.parrot.parrotapi.Services.User.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.parrot.parrotapi.Services.User.CreateUserRequest;
import com.parrot.parrotapi.Services.User.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request){
        var response = _userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GetUsersRequest>> getUsers(){
        var response = _userService.getUsers();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid UpdateUserRequest request){
        _userService.updateUser(request);
        return ResponseEntity.ok("");
    }

//    public ResponseEntity<FindUserResponse> getUser(String email){
//
//    }
}
