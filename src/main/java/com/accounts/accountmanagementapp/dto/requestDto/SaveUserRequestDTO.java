package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.Data;

import java.util.UUID;

@Data
public class SaveUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID role;
}