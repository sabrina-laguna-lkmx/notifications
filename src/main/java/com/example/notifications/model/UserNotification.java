package com.example.notifications.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
 
@Builder
@Data
public class UserNotification {

    @Id
    private String id;

    private String documentId;
    private UserNotificationType type;
    private String title;
    private String body;
    private String icon;
    private String reference;
    private String userId;
    private Boolean seen;
    private LocalDateTime createdAt;
    private LocalDateTime eventDate;
    
}
