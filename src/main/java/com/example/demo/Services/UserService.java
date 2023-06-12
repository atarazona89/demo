package com.example.demo.Services;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.GenericResponse;

public interface UserService {
    GenericResponse createUser(CreateUserRequest createUserRequest);
    GenericResponse login(LoginRequest loginRequest);
}
