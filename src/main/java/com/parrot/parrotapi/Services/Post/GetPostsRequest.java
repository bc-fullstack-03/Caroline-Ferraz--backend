package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetPostsRequest {

    private UUID id;
    private UUID userId;
    private LocalDateTime timestamp;
    private String description;
    private String photo;

    public GetPostsRequest(Post post){
        this.id = post.getId();
        this.userId = post.getUserId();
        this.timestamp = post.getTimestamp();
        this.description = post.getDescription();
        this.photo = post.getPhoto();
    }
}
