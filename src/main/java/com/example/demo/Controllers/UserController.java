package com.example.demo.Controllers;

import com.example.demo.Dtos.Requests.CreateUserRequest;
import com.example.demo.Dtos.Requests.LoginRequest;
import com.example.demo.Dtos.Responses.GenericResponse;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "health", method = RequestMethod.GET)
    public ResponseEntity<GenericResponse> health() {
        return new ResponseEntity<>(new GenericResponse<String>(HttpStatus.OK.value(), "I'm alive", null), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }
}
