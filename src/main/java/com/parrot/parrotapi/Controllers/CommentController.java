package com.parrot.parrotapi.Controllers;

import com.parrot.parrotapi.Services.Comment.CommentService;
import com.parrot.parrotapi.Services.Comment.CreateCommentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    @Autowired
    CommentService _commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody @Valid CreateCommentRequest request){
        var response = _commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
