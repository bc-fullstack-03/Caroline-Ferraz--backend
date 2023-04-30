package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Domain.User;
import com.parrot.parrotapi.Infrastructure.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
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
        return post.getId().toString();
    }

    public List<GetPostsRequest> getPosts(){
        return _postRepository.findAll().stream().map(GetPostsRequest::new).toList();
    }

    public void updatePost(UpdatePostRequest request){
        var optionalPost = _postRepository.findById(request.getId());
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
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
                post.getComment()
        );
    }
}
