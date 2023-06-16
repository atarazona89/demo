package com.example.demo.Dto.Response;

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

        // Por razones de seguridad no enviaré este campo
        // private String password;

        private List<PhoneResponse> phones;
}
