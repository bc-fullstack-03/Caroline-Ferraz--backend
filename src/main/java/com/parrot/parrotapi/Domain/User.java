package com.parrot.parrotapi.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


//@Data
//@Document
public class User {

//    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password){
        this.setId();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }
}
