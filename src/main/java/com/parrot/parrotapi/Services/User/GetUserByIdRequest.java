package com.parrot.parrotapi.Services.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetUserByIdRequest {

    private UUID id;
    private String name;
    private String photo;
    private List<UUID> friends;
    private List<UUID> following;
    private List<UUID> followers;
}
