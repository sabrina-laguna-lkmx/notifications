package com.example.notifications.data;

import java.util.List;

import com.example.notifications.model.UserNotification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends MongoRepository<UserNotification, String>{

    public List<UserNotification> findByType(String type);

    public List<UserNotification> findByTypeNot(String type);

    public List<UserNotification> findByUserId(String userId);
}