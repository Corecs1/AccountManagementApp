package com.accounts.accountmanagementapp.exception;

import javax.naming.AuthenticationException;

public class UserHasAlreadyExistException extends AuthenticationException {

    public UserHasAlreadyExistException() {
    }

    public UserHasAlreadyExistException(String explanation) {
        super(explanation);
    }
}
