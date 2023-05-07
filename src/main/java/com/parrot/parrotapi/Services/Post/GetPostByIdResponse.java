package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetPostByIdResponse {

    private UUID id;
    private UUID userId;
    private LocalDateTime timestamp;
    private String description;
    private String photo;
    private List<UUID> likes;
    private List<Comment> comments;
}
