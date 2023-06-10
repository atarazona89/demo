package com.example.demo.Dtos.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private List<PhoneRequest> phones;
}
