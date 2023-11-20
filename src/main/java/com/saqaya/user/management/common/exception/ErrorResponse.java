package com.saqaya.user.management.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String errorMessage;
    private String errorDetailsMessage;
    private boolean isBusinessError;
    private HttpStatus httpStatus;
    public static final ErrorResponse RESOURCE_NOT_FOUND = new ErrorResponse("RESOURCE_NOT_FOUND", "Resource not found", true,HttpStatus.NOT_FOUND);

}
