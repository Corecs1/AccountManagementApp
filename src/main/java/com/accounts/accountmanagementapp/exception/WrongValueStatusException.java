package com.accounts.accountmanagementapp.exception;

import org.springframework.security.core.AuthenticationException;

public class WrongValueStatusException extends AuthenticationException {

    public WrongValueStatusException(String msg) {
        super(msg);
    }

    public WrongValueStatusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
