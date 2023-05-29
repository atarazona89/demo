package com.example.demo.Dtos.Requests;

import lombok.Data;

@Data
public class PhoneRequest {
    private Long number;
    private Integer citycode;
    private String countrycode;
}
