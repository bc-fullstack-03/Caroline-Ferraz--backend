package com.parrot.parrotapi.Services.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Este campo não pode estar em branco")
    public String name;

    @NotBlank(message = "Este campo não pode estar em branco")
    @Email(message = "Este campo não contém um formato de e-mail válido")
    @Indexed(unique = true)
    public String email;

    @NotBlank(message = "Este campo não pode estar em branco")
    @Size(min = 8, max=25, message = "A Senha deve ter no mínimo 8 e no máximo 25 caracteres")
    public String password;
}
