package com.example.demo.Dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneResponse {
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
