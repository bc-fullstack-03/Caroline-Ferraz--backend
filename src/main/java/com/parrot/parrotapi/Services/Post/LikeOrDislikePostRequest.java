package com.parrot.parrotapi.Services.Post;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeOrDislikePostRequest {

    public UUID postId;
    public UUID userLike; // id of the user that's liking the post
}
