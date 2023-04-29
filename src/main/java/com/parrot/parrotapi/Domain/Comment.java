package com.parrot.parrotapi.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

@Data
public class Comment {

    @Id
    private UUID id;
    @DBRef
    private Post postId;
    @DBRef
    private User userId;
    private String text;
}
