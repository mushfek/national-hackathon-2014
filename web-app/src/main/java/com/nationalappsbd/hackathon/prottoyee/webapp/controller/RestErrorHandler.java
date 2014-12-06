package com.nationalappsbd.hackathon.prottoyee.webapp.controller;

import com.nationalappsbd.hackathon.prottoyee.webapp.exception.*;
import com.nationalappsbd.hackathon.prottoyee.webapp.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdullah Al Mamun Oronno
 */

@ControllerAdvice
public class RestErrorHandler {
    private MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(InvalidCredentialException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public com.nationalappsbd.hackathon.prottoyee.webapp.exception.Error processValidationError(InvalidCredentialException ex) {
        return new Error(1, "Invalid Credential");
    }
}
