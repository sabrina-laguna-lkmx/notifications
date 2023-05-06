package com.example.notifications.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialGroup {
    private String id;

    private List<String> products;
}
