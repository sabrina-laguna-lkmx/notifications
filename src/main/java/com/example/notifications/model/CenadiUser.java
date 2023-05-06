package com.example.notifications.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CenadiUser {
    @Id
    private String id;
    
    private String refreshToken;
    private String clientId;
    private List<String> roles;
    private Long exp;
    private LocalDateTime createdAt;
    private String lastName;
    private LocalDateTime passwordExpiration;
    private String phone;
    private String name;
    private String legalAccepted;
    private String email;
    private String username;
    private List<String> partners;
    private List<MaterialGroup> therapeuticLines;
    private List<String> clients;
    private List<String> billableClients;
    private List<String> clientGroups;
    private List<String> states;
    private List<String> programs;
    private List<Groups> groups;

}
