package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.User;
import lombok.Data;

import java.util.UUID;

@Data
public class GetUsersRequest {

    private UUID id;
    private String name;
    private String photo;

    public GetUsersRequest(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.photo = user.getPhoto();
    }
}
