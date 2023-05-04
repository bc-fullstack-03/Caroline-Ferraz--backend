package com.parrot.parrotapi.Services.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FindUserResponse {

    public UUID id;
    public String name;
    public String photo;
    public String email;
}
