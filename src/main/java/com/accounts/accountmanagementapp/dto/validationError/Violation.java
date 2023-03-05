package com.accounts.accountmanagementapp.dto.validationError;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {
    private final String message;
    private final String code;
    private final String rejectedValue;
}
