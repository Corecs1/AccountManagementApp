package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditPasswordRequestDTO {

    @NotBlank(message = "Password является обязательным полем")
    private String password;

    @NotBlank(message = "ConfirmPassword является обязательным полем")
    private String confirmPassword;
}
