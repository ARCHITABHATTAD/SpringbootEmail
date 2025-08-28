package com.app.pdf.access.dao;

import com.app.pdf.access.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDAO extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);
}
