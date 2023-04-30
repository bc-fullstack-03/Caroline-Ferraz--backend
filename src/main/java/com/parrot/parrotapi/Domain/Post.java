package com.parrot.parrotapi.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime timestamp;
    private String description;
    private String photo;
    @DBRef
    private List<User> likes;
    @DBRef
    private List<Comment> comment;

    public Post(UUID userId, String description, String photo){
        this.setId();
        this.userId = userId;
        this.setTimestamp();
        this.description = description;
        this.photo = photo;
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }
    protected void setTimestamp(){ this.timestamp = LocalDateTime.now(); }
}
