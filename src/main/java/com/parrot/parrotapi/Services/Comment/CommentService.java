package com.parrot.parrotapi.Services.Comment;

import com.parrot.parrotapi.Domain.Comment;
import com.parrot.parrotapi.Infrastructure.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository _commentRepository;

    public String createComment(CreateCommentRequest request){
        var comment = new Comment(request.postId, request.userId, request.text);
        _commentRepository.save(comment);
        return comment.getId().toString();
    }
}
