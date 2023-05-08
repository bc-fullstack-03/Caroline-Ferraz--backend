package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IPostService {

    String createPost(CreatePostRequest request);

//    String createPost(CreatePostRequest request, MultipartFile photoPost) throws Exception;

    Page<GetPostsResponse> getPosts(Pageable pageable);

    void updatePost(UpdatePostRequest request);

    void deletePost(UUID id);

    GetPostByIdResponse getPostById(UUID id);

    void addComment(Comment request);

    void removeComment(UUID postId, UUID id);

    void likeOrDislikePost(LikeOrDislikePostRequest request);

    Page<Post> getPostsByUser(UUID userId, Pageable pageable);

    Page<Post> getPostsbyUserFollowing(Pageable pageable);
}
