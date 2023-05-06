package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IPostRepository;
import com.parrot.parrotapi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository _postRepository;

    public String createPost(CreatePostRequest request){
        var post = new Post(request.userId, request.description, request.photo);
        _postRepository.save(post);
        //_userService.addPost(post);
        return post.getId().toString();
    }

    public List<GetPostsRequest> getPosts(){
        return _postRepository.findAll().stream().map(GetPostsRequest::new).toList();
    }

    public void updatePost(UpdatePostRequest request){
        var optionalPost = _postRepository.findById(request.getId());
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        post.updatePostData(request);
        _postRepository.save(post);
    }

    public void deletePost(UUID id) {
        _postRepository.deleteById(id);
    }

    public GetPostByIdRequest getPostById(UUID id) {
        var optionalPost = _postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        return new GetPostByIdRequest(
                post.getId(),
                post.getUserId(),
                post.getTimestamp(),
                post.getDescription(),
                post.getPhoto(),
                post.getLikes(),
                post.getComments()
        );
    }

    public void addComment(Comment request){
        var optionalPost = _postRepository.findById(request.getPostId());
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        post.addComment(request);
        _postRepository.save(post);
    }

    public void removeComment(UUID postId, UUID id){
        var optionalPost = _postRepository.findById(postId);
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        var comments = post.getComments();
        post.removeComment(comments, id);
        _postRepository.save(post);
    }

    public void likeOrDislikePost(LikeOrDislikePostRequest request){
        var optionalPost = _postRepository.findById(request.postId);
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        post.likeOrDislikePost(request.userLike);
        _postRepository.save(post);
    }

    public Page<Post> getPostsByUser(UUID userId, Pageable pageable){
        return _postRepository.findAllByUserId(userId, pageable);
    }

}
