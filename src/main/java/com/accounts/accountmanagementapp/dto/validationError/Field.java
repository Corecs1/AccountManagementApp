package com.accounts.accountmanagementapp.dto.validationError;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Field {
    private final Violation violation;
}
