package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    String createUser(CreateUserRequest request);

    Page<GetUsersResponse> getUsers(Pageable pageable);

    void updateUser(UpdateUserRequest request);

    void deleteUser(UUID id);

    GetUserByIdResponse getUserById(UUID id);

    Page<Post> getPostsByUser(UUID userId, Pageable pageable);

    void followOrUnfollowUser(UUID userId, FollowOrUnfollowUserRequest request);

    void addOrRemoveFollower(UUID userId, FollowOrUnfollowUserRequest request);

    //FindUserResponse findUserByEmail(String email);

    User getUser(String email);

    void uploadPhotoProfile(MultipartFile photoFile) throws Exception;
}