package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequestDTO {

    @NotBlank(message = "Email is an important field")
    @Email(message = "Incorrect email")
    private String email;

    @Pattern(message = "Family name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Family name is an important field")
    private String familyName;

    @Pattern(message = "Name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Name is an important field")
    private String name;

    @Pattern(message = "Middle name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    private String middleName;
}
