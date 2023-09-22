package com.example.demo.advice;

import com.example.demo.exceptions.IncorrectPasswordException;
import com.example.demo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String sqlIntegrityExceptionHandler(SQLIntegrityConstraintViolationException ex){
        return "SQLIntegrityConstraintViolationException " + ex;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        return "MethodArgumentNotValidException" + ex;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFound(UserNotFoundException ex){
        Map<String, String > error = new HashMap<>();
        error.put("errorMessage",ex.getMessage());
        return error;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exception(Exception ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IncorrectPasswordException.class)
    public String incorrectPassword(IncorrectPasswordException ex){
        return ex.getMessage();
    }

}
