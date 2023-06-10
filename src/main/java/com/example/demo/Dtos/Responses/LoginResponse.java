package com.example.demo.Dtos.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
        private UUID id;
        private Date created;
        private Date lastLogin;
        private String token;
        private Boolean isActive;
        private String name;
        private String email;
        private String password;
        private List<PhoneResponse> phones;
}
