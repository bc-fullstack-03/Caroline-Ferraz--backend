package com.parrot.parrotapi.Services.Authentication;

public interface IAuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest request);
}
