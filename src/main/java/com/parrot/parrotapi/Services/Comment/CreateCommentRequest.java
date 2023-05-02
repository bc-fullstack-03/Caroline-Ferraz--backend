package com.parrot.parrotapi.Services.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCommentRequest {

    @NotNull
    public UUID postId;
    @NotNull
    public UUID userId;
    @NotBlank
    @Size(max=800, message="O texto deve ter no máximo 800 caracteres")
    public String text;
}
