package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequestDTO {

    @NotBlank(message = "Email is an important field")
    @Email(message = "Incorrect email")
    private String email;

    @NotBlank(message = "Password is an important field")
    private String password;

    @Pattern(message = "Family name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Family name is an important field")
    private String familyName;

    @Pattern(message = "Name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Name is an important field")
    private String name;

    @Pattern(message = "Middle name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    private String middleName;

    @NotNull(message = "Role is an important field")
    private UUID role;
}