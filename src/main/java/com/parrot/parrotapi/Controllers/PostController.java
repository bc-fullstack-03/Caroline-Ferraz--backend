package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Services.Post.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<GetPostsResponse>> getPosts(@PageableDefault(size = 10, sort = {"timestamp"}) Pageable pageable){
        var response = _postService.getPosts(pageable);
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

    @GetMapping("/{id}")
    public ResponseEntity<GetPostByIdResponse> getPostById(@PathVariable UUID id){
        var response = _postService.getPostById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/comment")
    public ResponseEntity addComment(@RequestBody @Valid Comment request){
        _postService.addComment(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/comment/{id}")
    public ResponseEntity removeComment(@PathVariable UUID postId, @PathVariable UUID id){
        _postService.removeComment(postId, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/like")
    public ResponseEntity likePost(@RequestBody LikeOrDislikePostRequest request){
        _postService.likeOrDislikePost(request);
        return ResponseEntity.ok().build();
    }
}
