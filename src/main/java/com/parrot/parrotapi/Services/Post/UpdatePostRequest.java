package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdatePostRequest {

    @NotNull
    private UUID id;
    @Size(max=1000, message="O texto deve ter no máximo 1000 caracteres")
    private String description;
}
