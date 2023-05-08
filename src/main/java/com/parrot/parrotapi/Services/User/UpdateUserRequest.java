package com.parrot.parrotapi.Services.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;


@Data
public class UpdateUserRequest {

    public String name;
    @Email(message = "Este campo não contém um formato de e-mail válido")
    @Indexed(unique = true)
    public String email;
    @Size(min = 8, max=25, message = "A Senha deve ter no mínimo 8 e no máximo 25 caracteres")
    public String password;
}
