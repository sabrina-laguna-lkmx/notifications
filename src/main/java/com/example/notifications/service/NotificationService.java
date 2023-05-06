package com.example.notifications.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.notifications.data.CenadiUserRepository;
import com.example.notifications.data.UserNotificationRepository;
import com.example.notifications.model.UserNotification;
import com.example.notifications.model.UserNotificationType;
import com.example.notifications.service.command.DeliveryNotificationCommand;
import com.example.notifications.service.command.OrderConfirmedNotificationCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notifications/api/user-notifications")
public class NotificationService {

    @Autowired
    UserNotificationRepository repository;

    @Autowired
    CenadiUserRepository cenadiUserRepository;

    @PostMapping("/orders")
    public void addOrderCoonfirmedNotification(@RequestBody OrderConfirmedNotificationCommand command){

        List<UserNotification> notificationsList = cenadiUserRepository.findAll().stream()
        .map(user -> {
            UserNotification userNotification = UserNotification.builder()
            .documentId(command.getOrderId())
            .type(UserNotificationType.CONFIRMED)
            .title(command.getClientOrderId())
            .body(getNotificationBody(command.getUmuBudgetCode(), command.getUmuName()))
            .icon(command.getOrderType())
            .reference(getOrderConfirmedReference(command.getOrderId()))
            .userId(user.getId())
            .seen(Boolean.FALSE)
            .createdAt(LocalDateTime.now())
            .eventDate(command.getEventDate())
            .build();

            return userNotification;

        }).collect(Collectors.toList());

        repository.saveAll(notificationsList);
    }

    @PostMapping("/deliveries")
    public void addDeliveryNotification(@RequestBody DeliveryNotificationCommand command){
        
        List<UserNotification> notificationsList = cenadiUserRepository.findAll().stream()
        .map(user -> {
            UserNotification userNotification = UserNotification.builder()
            .documentId(command.getOrderId())
            .type(UserNotificationType.valueOf(command.getNotificationType()))
            .title(getDeliveryNotificationTitle(command.getClientOrderId(), command.getDeliveryId()))
            .body(getNotificationBody(command.getUmuBudgetCode(), command.getUmuName()))
            .icon(command.getDeliveryType())
            .reference(getDeliveryReference(command.getOrderId(), command.getDeliveryId()))
            .userId(user.getId())
            .seen(Boolean.FALSE)
            .createdAt(LocalDateTime.now())
            .eventDate(command.getEventDate())
            .build();

            return userNotification;

        }).collect(Collectors.toList());

        repository.saveAll(notificationsList);
    }
    

    private String getNotificationBody(String umuBudgetCode, String umuName) {
        return String.format("%s - %s", umuBudgetCode, umuName);
    }

    private String getOrderConfirmedReference(String orderId) {
        return String.format("/orders/%s", orderId);
    }

    private String getDeliveryNotificationTitle(String clientOrderId, String deliveryId) {
        if(deliveryId.startsWith("058")){
            return clientOrderId;
        }
        String remission = deliveryId.substring(3,10);
        return String.format("%s %s", clientOrderId, remission);
    }

    private String getDeliveryReference(String orderId, String deliveryId) {
        return String.format("/orders/%s?deliveryId=%s", orderId, deliveryId);
    }

    @GetMapping("/orders")
    public List<UserNotification> getOrdersNotifications(){
        return repository.findByType("CONFIRMED");
    }

    @GetMapping("/deliveries")
    public List<UserNotification> getDeliveriesNotifications(){
        return repository.findByTypeNot("CONFIRMED");
    }

    @GetMapping(value = "", params = "user-id")
    public List<UserNotification> getNotificationByUser(@RequestParam(name = "user-id") String id){
        return repository.findByUserId(id);
    }

    /* 
    @DeleteMapping("")
    public String deletedAll(){
        repository.deleteAll();
        return "Deleted All";
    } */
}

