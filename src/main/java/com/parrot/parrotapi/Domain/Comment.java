package com.parrot.parrotapi.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
//@Document
public class Comment {

    @Id
    private UUID id;
    //@DBRef(lazy = true)
    //@JsonBackReference
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

    public void updateCommentData(Comment data){
        if(data.getText() != null){
            this.text = data.getText();
        }
    }

}
