package com.accounts.accountmanagementapp.exception;

import org.springframework.security.core.AuthenticationException;

public class UserHasAlreadyExistException extends AuthenticationException {

    public UserHasAlreadyExistException(String explanation) {
        super(explanation);
    }

    public UserHasAlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
