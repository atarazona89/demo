package com.example.demo;

import com.example.demo.Config.DemoApplication;
import com.example.demo.Dto.Request.CreateUserRequest;
import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Dto.Response.GenericResponse;
import com.example.demo.Dto.Response.LoginResponse;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Implementation.UserServiceImpl;
import com.example.demo.Util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DemoApplication.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegisterUser() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        CreateUserRequest userRegistrationDTO = new CreateUserRequest();
        userRegistrationDTO.setName("John Doe");
        userRegistrationDTO.setEmail("johndoe@example.com");
        userRegistrationDTO.setPassword("Password12");

        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded");

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
        String password = "Password12";
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Jon Doe");
        userEntity.setEmail(email);

        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded");
        when(passwordEncoder.matches(any(),any())).thenReturn(Boolean.TRUE);
        userEntity.setPassword(passwordEncoder.encode(password));

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setEmail(email);
        GenericResponse loginResponseDTO = userService.login(loginRequest);

        // Assert
        assertNotNull(((LoginResponse) loginResponseDTO.getPayload()).getToken());
    }
}

