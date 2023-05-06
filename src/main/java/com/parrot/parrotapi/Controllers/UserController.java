package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Services.Security.IJwtService;
import com.parrot.parrotapi.Services.User.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request){
//        if(!_jwtService.isValidToken(getToken(), getUserId())){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
//        }

        var response = _userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<GetUsersResponse>> getUsers(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var response = _userService.getUsers(pageable);
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

    @PutMapping("/{userId}")
    public ResponseEntity followOrUnfollowUser(@PathVariable UUID userId, @RequestBody FollowOrUnfollowUserRequest id){
        _userService.followOrUnfollowUser(userId, id);
        _userService.addOrRemoveFollower(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/uploadPhoto")
    public ResponseEntity uploadPhotoProfile(@RequestParam("photo") MultipartFile photoFile){
        try {
            _userService.uploadPhotoProfile(photoFile);
            return new ResponseEntity(HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public String getToken(){
        var jwt = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(("Authorization"));
        return jwt.substring(7);
    }

    public String getUserId(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(("UserId"));
    }
}