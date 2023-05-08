package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreatePostRequest {

    @NotNull
    public UUID userId;
    @Size(max=1000, message="O texto deve ter no máximo 1000 caracteres")
    public String description;
}
