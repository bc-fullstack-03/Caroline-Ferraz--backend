package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {

    public List<Post> findAllByUserId(UUID userId);
}
