package com.authentication.msauthentication.Exeception;

import jakarta.ws.rs.NotFoundException;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class Handler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerInvalidArgument(MethodArgumentNotValidException e){
        Map<String,String> erroMap=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            erroMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return erroMap;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String,String> UserNotFound(UsernameNotFoundException e){
        Map<String,String> erroMap= new HashMap<>();
        erroMap.put("ErrorEmail",e.getMessage());
        return erroMap;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PasswordException.class)
    public Map<String,String> passwordIncorrect(PasswordException e){
        Map<String,String> erroMap= new HashMap<>();
        erroMap.put("ErrorPassword",e.getMessage());
        return erroMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String,String> FetchEmail(PasswordException e){
        Map<String,String> erroMap= new HashMap<>();
        erroMap.put("ErrorEmail",e.getMessage());
        return erroMap;
    }
}
