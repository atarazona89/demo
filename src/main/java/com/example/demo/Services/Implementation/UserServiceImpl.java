package com.example.demo.Services.Implementation;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.CreateUserResponse;
import com.example.demo.Dtos.Responses.LoginResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import com.example.demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = objectMapper.convertValue(createUserRequest, UserEntity.class);
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        userEntity.setPassword(encodedPassword);
        userEntity.setIsActive(Boolean.TRUE);

        UserEntity save = userRepository.save(userEntity);
        CreateUserResponse response = objectMapper.convertValue(save, CreateUserResponse.class);
        response.setToken(JwtUtil.generateToken(userEntity));
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        // Generar el token JWT
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            String token = JwtUtil.generateToken(userEntity);
            LoginResponse response = objectMapper.convertValue(userEntity, LoginResponse.class);
            response.setToken(token);

            return response;
        }
        return null;
    }
}
