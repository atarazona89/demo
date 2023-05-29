package com.example.demo.Services;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.CreateUserResponse;
import com.example.demo.Dtos.Responses.LoginResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    LoginResponse login(LoginRequest loginRequest);
}
