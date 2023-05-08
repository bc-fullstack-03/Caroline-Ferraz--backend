package com.parrot.parrotapi.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Comment {

    @Id
    private UUID id;
    private UUID postId;
    private UUID userId; // user's id that've made the comment
    private String text;

    public Comment(UUID postId,  UUID userId, String text){
        setId();
        this.postId = postId;
        this.userId = userId;
        this.text = text;
    }

    protected void setId() { this.id = UUID.randomUUID(); }
}
