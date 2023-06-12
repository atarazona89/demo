package com.example.demo.Dtos.Error;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class GenericError {
    private List<ErrorDetail> error;

    public GenericError(ErrorDetail error) {
        this.error = Arrays.asList(new ErrorDetail[]{error});
    }

    public GenericError(List<ErrorDetail> error) {
        this.error = error;
    }
}
