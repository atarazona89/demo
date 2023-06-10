package com.example.demo.Dtos.Responses;

import com.example.demo.Dtos.Requests.PhoneRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {

    private UUID id;
    private String name;
    private String email;
    private Date created;
    private Date lastLogin;
    private Boolean isActive;
    private String token;
    private List<PhoneRequest> phones;
}
