package com.example.demo.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @Email(message = "El email debe ser válido")
    private String email;

    @Size(min = 8, max = 12, message = "La clave debe tener entre 8 y 12 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z\\d]+$",
            message = "La clave debe tener una mayúscula y dos números, en combinación de letras minúsculas")
    private String password;
    private List<PhoneRequest> phones;
}
