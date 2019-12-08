package com.fyle.service.exception;

import com.fyle.service.response.BaseResponse;
import com.fyle.service.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class BankSearchServiceGlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankSearchServiceGlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleNumberFormatException(NumberFormatException numberFormatException){
        LOGGER.error("Number format exception for bubbled up, sending response with appropriate code and message");
            return ResponseBuilder.buildBaseResponse(1600, "Parameters offset and limit should be numeric and positive values, with minimum values 0 and 1 respectively.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleConstraintViolationExceptions(ConstraintViolationException constraintViolationException){
        StringBuilder stringBuilder = new StringBuilder();
        constraintViolationException.getConstraintViolations()
                .forEach(constraintViolation -> {
                    stringBuilder.append("Received Input : ")
                            .append(constraintViolation.getInvalidValue())
                            .append(" , ")
                            .append("Message : ")
                            .append(constraintViolation.getMessage());
                });
        return ResponseBuilder.buildBaseResponse(1600, stringBuilder.toString());
    }
}