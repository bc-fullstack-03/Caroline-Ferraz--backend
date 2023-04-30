package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Services.Post.CreatePostRequest;
import com.parrot.parrotapi.Services.Post.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    private PostService _postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody @Valid CreatePostRequest request){
        var response = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
