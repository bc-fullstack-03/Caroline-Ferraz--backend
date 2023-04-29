package com.parrot.parrotapi.Services.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    String createUser(CreateUserRequest request);

    List<GetUsersRequest> getUsers();

    void updateUser(UpdateUserRequest request);

    void deleteUser(UUID id);
}
