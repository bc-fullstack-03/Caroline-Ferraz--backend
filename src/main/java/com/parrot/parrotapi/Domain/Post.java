package com.parrot.parrotapi.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.UUID;

@Data
public class Post {

    @Id
    private UUID id;
    @DBRef
    private User author;
    private String description;
    private String photo;
    private List<User> likes;
    private List<Comment> comment;
}
