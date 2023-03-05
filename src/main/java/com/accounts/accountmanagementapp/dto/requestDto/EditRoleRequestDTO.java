package com.accounts.accountmanagementapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditRoleRequestDTO {
    @NotNull(message = "Role является обязательным полем")
    private UUID role;
}
