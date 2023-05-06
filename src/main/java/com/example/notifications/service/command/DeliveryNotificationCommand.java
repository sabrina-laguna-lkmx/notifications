package com.example.notifications.service.command;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DeliveryNotificationCommand {
    private String orderId;
    private String clientOrderId;
    private String deliveryId;
    private String umuCode;
    private String umuBudgetCode;
    private String umuName;
    private String notificationType;
    private String deliveryType;
    private List<String> classifications;
    @NonNull
    private LocalDateTime eventDate;
    private String delegation;    
}
