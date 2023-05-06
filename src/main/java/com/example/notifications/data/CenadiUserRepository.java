package com.example.notifications.data;

import java.util.Optional;

import com.example.notifications.model.CenadiUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenadiUserRepository extends MongoRepository<CenadiUser, String>{
    Optional<CenadiUser> findById(String id);
}
