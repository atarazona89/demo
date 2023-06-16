package com.example.demo.Service;

import com.example.demo.Dto.Request.CreateUserRequest;
import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Dto.Response.GenericResponse;

public interface UserService {
    GenericResponse createUser(CreateUserRequest createUserRequest);
    GenericResponse login(LoginRequest loginRequest);
}
