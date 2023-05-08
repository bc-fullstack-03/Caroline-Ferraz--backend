package com.parrot.parrotapi.Domain;

import com.parrot.parrotapi.Services.Post.UpdatePostRequest;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    @Id
    private UUID id;
    private UUID userId;
    private LocalDateTime timestamp;
    private String description;
    private String photo;
    private List<UUID> likes; // id's of the users that liked the post
    private List<Comment> comments;

    public Post(UUID userId, String description, String photo){
        this.setId();
        this.userId = userId;
        this.setTimestamp();
        this.description = description;
        this.photo = photo;
        this.likes = new ArrayList<UUID>();
        this.comments = new ArrayList<Comment>();
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }
    protected void setTimestamp(){ this.timestamp = LocalDateTime.now(); }

    public void updatePostData(UpdatePostRequest data){
        if(data.getDescription() != null){
            this.description = data.getDescription();
        }
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(List<Comment> comments, UUID id){
        for(Comment comment : comments){
            if(comment.getId().equals(id)){
                comments.remove(comment);
                break;
            }
        }
    }

    public void likeOrDislikePost(UUID userId) {
        if(likes.contains(userId)) {
            likes.remove(userId);
        }
        else {
            this.likes.add(userId);
        }
    }
}
