package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IUserService {

    String createUser(CreateUserRequest request);

    Page<GetUsersResponse> getAllUsers(Pageable pageable);

    void updateUser(UpdateUserRequest request);

    void deleteUser(UUID id);

    GetUserByIdResponse getUserById(UUID id);

    void followOrUnfollowUser(UUID userId);

    void addOrRemoveFollower(UUID userId);

    User getUser(String email);

    void uploadPhotoProfile(MultipartFile photoFile) throws Exception;

    User getUserBySecurityContextHolder();
}