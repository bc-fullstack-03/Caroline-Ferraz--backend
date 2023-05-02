package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetPostByIdRequest {

    private UUID id;
    private UUID userId;
    private LocalDateTime timestamp;
    private String description;
    private String photo;
    private List<User> likes;
    private List<Comment> comment;
}
