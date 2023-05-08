package com.parrot.parrotapi.Services.Post;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Domain.Post;
import com.parrot.parrotapi.Infrastructure.IPostRepository;
import com.parrot.parrotapi.Services.FileUpload.IFileUploadService;
import com.parrot.parrotapi.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class PostService implements IPostService {


    @Autowired
    private IPostRepository _postRepository;

    @Autowired
    private IFileUploadService _fileUploadService;

    @Autowired
    private IUserService _userService;

    public String createPost(CreatePostRequest request){
        var post = new Post(request.userId, request.description, request.photo);
        _postRepository.save(post);
        return post.getId().toString();
    }

//    public String createPost(CreatePostRequest request, MultipartFile photoPost) throws Exception {
//        var userId = _userService.getUserBySecurityContextHolder().getId();
//        var post = new Post(userId, request.description, this.uploadPhotoPost(photoPost, userId));
//        _postRepository.save(post);
//        return post.getId().toString();
//    }

    public Page<GetPostsResponse> getPosts(Pageable pageable){
        return _postRepository.findAll(pageable).map(GetPostsResponse::new);
    }

    public void updatePost(UpdatePostRequest request){
        var post = this.getPostByPostId(request.getId());
        post.updatePostData(request);
        _postRepository.save(post);
    }

    public void deletePost(UUID id) {
        _postRepository.deleteById(id);
    }

    public GetPostByIdResponse getPostById(UUID id) {
        var post = this.getPostByPostId(id);
        return new GetPostByIdResponse(
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
        var post = this.getPostByPostId(request.getPostId());
        post.addComment(request);
        _postRepository.save(post);
    }

    public void removeComment(UUID postId, UUID id){
        var post = this.getPostByPostId(postId);
        var comments = post.getComments();
        post.removeComment(comments, id);
        _postRepository.save(post);
    }

    public void likeOrDislikePost(LikeOrDislikePostRequest request){
        var post = this.getPostByPostId(request.postId);
        post.likeOrDislikePost(request.userLike);
        _postRepository.save(post);
    }

    public Page<Post> getPostsByUser(UUID userId, Pageable pageable){
        return _postRepository.findAllByUserId(userId, pageable);
    }

    public Page<Post> getPostsbyUserFollowing(Pageable pageable) {
        var following = _userService.getUserBySecurityContextHolder().getFollowing();
        List<Post> posts = new ArrayList<>();
        for (UUID id : following) {
            Page<Post> postsByUser = _postRepository.findAllByUserId(id, pageable);
            posts.addAll(postsByUser.getContent());
        }
        return new PageImpl<>(posts, pageable, posts.size());
    }

//    private void uploadPhotoPost(MultipartFile photoPost, UUID id) throws Exception {
//        var post = this.getPostByPostId(id);
//        var photo = "";
//        try{
//            var fileName = id + "." + photoPost.getOriginalFilename().substring(photoPost.getOriginalFilename().lastIndexOf(".") + 1);
//            photo = _fileUploadService.upload(photoPost, fileName);
//
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//        post.setPhoto(photo);
//    }

    private String uploadPhotoPost(MultipartFile photoPost, UUID id) throws Exception {
        var photo = "";
        try{
            var fileName = id + "." + photoPost.getOriginalFilename().substring(photoPost.getOriginalFilename().lastIndexOf(".") + 1);
            photo = _fileUploadService.upload(photoPost, fileName);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return photo;
    }

    private Post getPostByPostId(UUID id){
        var optionalPost = _postRepository.findById(id);
        Post post = optionalPost.orElseThrow(() -> new NoSuchElementException("Post não encontrado"));
        return post;
    }
}
