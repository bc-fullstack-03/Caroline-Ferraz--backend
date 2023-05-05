package com.parrot.parrotapi.Services.Authentication;

import lombok.Data;

@Data
public class AuthenticateRequest {

    public String email;
    public String password;
}
