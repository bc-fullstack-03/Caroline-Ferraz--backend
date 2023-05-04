package com.parrot.parrotapi.Services.User;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    String createUser(CreateUserRequest request);

    List<GetUsersRequest> getUsers();

    void updateUser(UpdateUserRequest request);

    void deleteUser(UUID id);

    GetUserByIdRequest getUserById(UUID id);

    List<Post> getPostsByUser(UUID userId);

    void followOrUnfollowUser(UUID userId, FollowOrUnfollowUserRequest request);

    void addOrRemoveFollower(UUID userId, FollowOrUnfollowUserRequest request);

    //void addPost(Post post);

    FindUserResponse findUserByEmail(String email);

    User getUser(String email);
}
