package com.authentication.msauthentication.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionAddRoleToUser extends RuntimeException {
    public ExceptionAddRoleToUser(String s) {
        super(s);
    }
}
