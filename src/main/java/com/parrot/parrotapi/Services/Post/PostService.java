package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
