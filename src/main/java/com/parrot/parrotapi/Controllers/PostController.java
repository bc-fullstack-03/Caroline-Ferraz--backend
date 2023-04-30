package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Services.Post.CreatePostRequest;
import com.parrot.parrotapi.Services.Post.GetPostsRequest;
import com.parrot.parrotapi.Services.Post.PostService;
import com.parrot.parrotapi.Services.Post.UpdatePostRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<GetPostsRequest>> getPosts(){
        var response = _postService.getPosts();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UpdatePostRequest> updatePost(@RequestBody @Valid UpdatePostRequest request){
        _postService.updatePost(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable UUID id) {
        _postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
