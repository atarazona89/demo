package com.example.demo.Dtos.Requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
