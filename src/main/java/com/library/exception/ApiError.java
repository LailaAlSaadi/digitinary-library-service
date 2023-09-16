package com.library.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        errors = Collections.singletonList(error);
    }
}