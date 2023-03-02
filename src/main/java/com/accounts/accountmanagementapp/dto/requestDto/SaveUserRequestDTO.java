package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID role;
}