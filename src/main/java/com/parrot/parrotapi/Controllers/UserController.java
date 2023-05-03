package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Services.User.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        _userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdRequest> getUserById(@PathVariable UUID id){
        var user = _userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable UUID id){
        var posts =  _userService.getPostsByUser(id);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("{userId}")
    public ResponseEntity followOrUnfollowUser(@PathVariable UUID userId, @RequestBody FollowOrUnfollowUserRequest id){
        _userService.followOrUnfollowUser(userId, id);
        _userService.addOrRemoveFollower(userId, id);
        return ResponseEntity.noContent().build();
    }
}
