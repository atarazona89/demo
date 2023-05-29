package com.example.demo.Entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity(name = "phone")
@Data
public class PhoneEntity {
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
