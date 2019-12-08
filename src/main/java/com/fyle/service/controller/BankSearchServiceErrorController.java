package com.fyle.service.controller;

import com.fyle.service.response.BaseResponse;
import com.fyle.service.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankSearchServiceErrorController implements ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankSearchServiceErrorController.class);
    private Integer code;
    private String message;

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse errorResponse(){
        LOGGER.info("Requested resource cannot be served, sending back error response");
        return ResponseBuilder.buildBaseResponse(code, message);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @Autowired
    public void setCode(@Value("${fyle.search.service.resource.not.found.code}") Integer code) {
        this.code = code;
    }

    @Autowired
    public void setMessage(@Value("${fyle.search.service.resource.not.found.message}") String message) {
        this.message = message;
    }
}
