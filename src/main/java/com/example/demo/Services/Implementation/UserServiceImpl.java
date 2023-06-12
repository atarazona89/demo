package com.example.demo.Services.Implementation;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.CreateUserResponse;
import com.example.demo.Dtos.Responses.GenericResponse;
import com.example.demo.Dtos.Responses.LoginResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import com.example.demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public GenericResponse createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = objectMapper.convertValue(createUserRequest, UserEntity.class);
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        userEntity.setPassword(encodedPassword);
        userEntity.setIsActive(Boolean.TRUE);

        UserEntity save = userRepository.save(userEntity);
        CreateUserResponse response = objectMapper.convertValue(save, CreateUserResponse.class);
        response.setToken(JwtUtil.generateToken(userEntity));
        return new GenericResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.toString(), response);
    }

    @Override
    public GenericResponse login(LoginRequest loginRequest) {

        // Generar el token JWT
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            String token = JwtUtil.generateToken(userEntity);
            LoginResponse response = objectMapper.convertValue(userEntity, LoginResponse.class);
            response.setToken(token);

            return new GenericResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), response);
        }
        return new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "Error en email o contraseña", new LoginResponse());
    }
}
