package com.example.demo;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.GenericResponse;
import com.example.demo.Dtos.Responses.LoginResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import com.example.demo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtTokenUtil;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CreateUserRequest userRegistrationDTO = new CreateUserRequest();
        userRegistrationDTO.setName("John Doe");
        userRegistrationDTO.setEmail("johndoe@example.com");
        userRegistrationDTO.setPassword("password123");

        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());

        // Act
        userService.createUser(userRegistrationDTO);

        // Assert
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void testLoginUser() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String email = "johndoe@example.com";
        String password = "password123";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(jwtTokenUtil.generateToken(userEntity)).thenReturn("testToken");

        // Act
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setEmail(email);
        GenericResponse loginResponseDTO = userService.login(loginRequest);

        // Assert
        assertNotNull(((LoginResponse) loginResponseDTO.getPayload()).getToken());
    }
}

