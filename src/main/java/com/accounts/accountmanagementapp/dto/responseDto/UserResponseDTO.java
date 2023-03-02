package com.accounts.accountmanagementapp.dto.responseDto;

import com.accounts.accountmanagementapp.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;
    private UUID role;
    private Status status;
    private LocalDateTime createdAt;
}
