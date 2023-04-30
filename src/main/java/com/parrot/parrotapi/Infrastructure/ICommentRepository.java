package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ICommentRepository extends MongoRepository<Comment, UUID> {
}
