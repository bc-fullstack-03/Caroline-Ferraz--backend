package com.parrot.parrotapi.Domain;

import com.parrot.parrotapi.Services.User.UpdateUserRequest;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
public class User {

    @Id
    private UUID id;
    private String name;
    private String photo;
    private String email;
    private String password;
    private List<UUID> following; // id's of the users that the user is following
    private List<UUID> followers; // id's of the users that are followers

    public User(String name, String email, String password){
        this.setId();
        this.name = name;
        this.setPhoto();
        this.email = email;
        this.password = password;
        this.following = new ArrayList<UUID>();
        this.followers = new ArrayList<UUID>();
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

    public void followOrUnfollowUser(UUID userId){
        if(following.contains(userId)) {
            following.remove(userId);
        }
        else {
            this.following.add(userId);
        }
    }

    public void addOrRemoveFollower(UUID id){
        if(followers.contains(id)) {
            followers.remove(id);
        }
        else {
            this.followers.add(id);
        }
    }
}
