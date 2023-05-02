package com.parrot.parrotapi.Domain;

import com.parrot.parrotapi.Services.User.GetUserByIdRequest;
import com.parrot.parrotapi.Services.User.UpdateUserRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;


@Data
//@Document
public class User {

    @Id
    private UUID id;
    private String name;
    private String photo;
    private String email;
    private String password;
    @DBRef
    private List<User> friends;
    @DBRef
    private List<User> following;
    @DBRef
    private List<User> followers;

    public User(String name, String email, String password){
        this.setId();
        this.name = name;
        this.setPhoto();
        this.email = email;
        this.password = password;
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }

    public void setPhoto() { this.photo = "https://i.imgur.com/zHoVjaF.jpeg"; }

    public void updateUserData(UpdateUserRequest data) {
        if(data.getName() != null){
            this.name = data.getName();
        }
        if(data.getPhoto() != null){
            this.photo = data.getPhoto();
        }
        if(data.getEmail() != null){
            this.email = data.getEmail();
        }
        if(data.getPassword() != null){
            this.password = data.getPassword();
        }
    }

}
