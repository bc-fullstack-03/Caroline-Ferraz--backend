package com.parrot.parrotapi.Services.User;

import lombok.Data;

@Data
public class CreateUserRequest {

    public String name;
    public String email;
    public String password;
}
