package com.parrot.parrotapi.Services.Authentication;

import com.parrot.parrotapi.Services.Security.IJwtService;
import com.parrot.parrotapi.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var user = _userService.getUserByEmail(request.email);

        if(user == null){
            throw new RuntimeException("Senha ou Usuário incorretos.");
        }
        if(!_passwordEncoder.matches(request.password, user.getPassword())){
            throw new RuntimeException("Senha ou Usuário incorretos.");
        }

        var token = _jwtService.generateToken(user.getId());

        var response = new AuthenticateResponse();
        response.setUserId(user.getId());
        response.setToken(token);
        return response;
    }
}
