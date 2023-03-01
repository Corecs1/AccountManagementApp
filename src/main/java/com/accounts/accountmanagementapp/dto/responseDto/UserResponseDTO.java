package com.accounts.accountmanagementapp.dto.responseDto;

import com.accounts.accountmanagementapp.model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponseDTO {
    /*
    save suer + edit user + edit password + edit role + edit status + user
    {
        "id": "a78b9730-dea9-4a2c-a97c-011424117d1b",
            "email": "test@test.ru",
            "familyName": "Иванов",
            "name": "Иван",
            "middleName": null,
            "role": "a78b9730-dea9-4a2c-a97c-011424117d1b",
            "status": "active",
            "createdAt": "2019-07-31T10:00:00"
    }
    */

    private UUID id;
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;
    private UUID role;
    private Status status;
    private LocalDateTime createdAt;
}
