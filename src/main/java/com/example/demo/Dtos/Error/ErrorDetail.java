package com.example.demo.Dtos.Error;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetail {
    private Date timestamp;
    private Integer codigo;
    private String detail;

    public ErrorDetail(Integer codigo, String detail) {
        this.timestamp = new Date();
        this.codigo = codigo;
        this.detail = detail;
    }
}
