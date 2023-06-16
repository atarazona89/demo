package com.example.demo.Dto.Error;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class GenericError {
    private List<ErrorDetail> error;

    public GenericError(ErrorDetail error) {
        this.error = Arrays.asList(error);
    }

    public GenericError(List<ErrorDetail> error) {
        this.error = error;
    }
}
