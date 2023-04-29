package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.User;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetUserByIdRequest {

    private UUID id;
    private String name;
    private String photo;
    private List<User> friends;
    private List<User> following;
    private List<User> followers;
}
