package com.parrot.parrotapi.Services.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    String createUser(CreateUserRequest request);

    List<GetUsersRequest> getUsers();
}
