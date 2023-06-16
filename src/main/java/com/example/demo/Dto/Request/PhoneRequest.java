package com.example.demo.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneRequest {
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
