package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Services.User.GetUserByIdRequest;
import com.parrot.parrotapi.Services.User.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    String createPost(CreatePostRequest request);

    List<GetPostsRequest> getPosts();

    //void updatePost(UpdatePostRequest request);

    //void deletePost(UUID id);

    //GetPostByIdRequest getPostById(UUID id);
}
