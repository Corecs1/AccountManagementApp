package com.accounts.accountmanagementapp.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordMismatchException extends AuthenticationException {

    public PasswordMismatchException(String msg) {
        super(msg);
    }

    public PasswordMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
