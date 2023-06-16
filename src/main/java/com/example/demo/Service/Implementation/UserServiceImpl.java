package com.example.demo.Service.Implementation;

import com.example.demo.Dto.Request.CreateUserRequest;
import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Dto.Response.CreateUserResponse;
import com.example.demo.Dto.Response.GenericResponse;
import com.example.demo.Dto.Response.LoginResponse;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.Util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public GenericResponse createUser(CreateUserRequest createUserRequest) {
        logger.info("Registrando usuario: ", createUserRequest.getName());
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
        logger.info("login in: ", loginRequest.getEmail());

        // Generar el token JWT
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword()) || userEntity == null) {
            String token = JwtUtil.generateToken(userEntity);
            LoginResponse response = objectMapper.convertValue(userEntity, LoginResponse.class);
            response.setToken(token);

            return new GenericResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), response);
        }

        logger.warn("Error en email o contraseña");
        return new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "Error en email o contraseña", new LoginResponse());
    }
}
