package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
}
