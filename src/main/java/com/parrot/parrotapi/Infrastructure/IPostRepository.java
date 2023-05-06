package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {

    public Page<Post> findAllByUserId(UUID userId, Pageable pageable);
}
