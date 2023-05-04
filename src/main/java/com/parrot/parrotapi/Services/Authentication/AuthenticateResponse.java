package com.parrot.parrotapi.Services.Authentication;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthenticateResponse {

    public UUID userId;
    public String token;
}
