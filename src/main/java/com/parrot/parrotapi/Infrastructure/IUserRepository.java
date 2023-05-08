package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {

    public User findUserByEmail(String email);

    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    public Page<User> findByNameIgnoreCase(String name, Pageable pageable);
}
