package com.example.demo.Dtos.Requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private List<PhoneRequest> phones;
}
