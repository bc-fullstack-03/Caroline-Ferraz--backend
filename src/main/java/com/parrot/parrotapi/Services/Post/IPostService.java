package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    String createPost(CreatePostRequest request);

    List<GetPostsRequest> getPosts();

    void updatePost(UpdatePostRequest request);

    void deletePost(UUID id);

    GetPostByIdRequest getPostById(UUID id);

    void addComment(Comment request);

    void removeComment(UUID postId, UUID id);

    void likeOrDislikePost(LikeOrDislikePostRequest request);
}
