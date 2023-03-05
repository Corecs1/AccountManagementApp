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

    @NotBlank(message = "Email является обязательным полем")
    @Email(message = "Некоректный email")
    private String email;

    @Pattern(message = "Введите корректную фамилию", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Family является обязательным полем")
    private String familyName;

    @Pattern(message = "Введите корректное имя", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    @NotBlank(message = "Name является обязательным полем")
    private String name;

    @Pattern(message = "Введите корректное отчество", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]+")
    private String middleName;
}
