package com.example.demo.Services.Implementation;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.CreateUserResponse;
import com.example.demo.Dtos.Responses.LoginResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        UserEntity save = userRepository.save(objectMapper.convertValue(createUserRequest, UserEntity.class));
        return objectMapper.convertValue(save,CreateUserResponse.class);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}
