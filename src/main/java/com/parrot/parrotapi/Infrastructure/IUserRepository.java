package com.parrot.parrotapi.Infrastructure;

import com.parrot.parrotapi.Domain.User;
import com.parrot.parrotapi.Services.User.FindUserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {

    public User findUserByEmail(String email);
}
