package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
}
