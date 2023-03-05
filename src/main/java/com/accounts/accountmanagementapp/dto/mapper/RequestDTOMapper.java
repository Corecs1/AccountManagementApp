package com.accounts.accountmanagementapp.dto.mapper;

import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.model.Role;
import com.accounts.accountmanagementapp.model.Status;
import com.accounts.accountmanagementapp.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RequestDTOMapper {

    public User apply(SaveUserRequestDTO saveUserRequestDTO, Role role, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(saveUserRequestDTO.getEmail())
                .password(passwordEncoder.encode(saveUserRequestDTO.getPassword()))
                .name(saveUserRequestDTO.getName())
                .familyName(saveUserRequestDTO.getFamilyName())
                .middleName(saveUserRequestDTO.getMiddleName())
                .role(role)
                .status(Status.ACTIVE)
                .build();
    }

    public User apply(EditUserRequestDTO editUserRequestDTO, User user) {
        user.setEmail(editUserRequestDTO.getEmail());
        user.setFamilyName(editUserRequestDTO.getFamilyName());
        user.setName(editUserRequestDTO.getName());
        user.setMiddleName(editUserRequestDTO.getMiddleName());
        return user;
    }
}
