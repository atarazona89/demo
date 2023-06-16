package com.example.demo.Dto.Response;

import lombok.Data;

@Data
public class GenericResponse<T> {
    private Integer status;
    private String message;
    private T payload;

    public GenericResponse(Integer status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }
}
