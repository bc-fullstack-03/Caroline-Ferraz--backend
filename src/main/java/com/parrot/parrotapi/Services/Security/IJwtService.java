package com.parrot.parrotapi.Services.Security;

import java.util.UUID;

public interface IJwtService {

    String generateToken(UUID userId);
    boolean isValidToken(String token, String userId);

}
