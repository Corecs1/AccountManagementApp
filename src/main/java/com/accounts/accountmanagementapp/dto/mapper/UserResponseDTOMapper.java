package com.accounts.accountmanagementapp.dto.mapper;

import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserResponseDTOMapper implements Function<User, UserResponseDTO> {
    @Override
    public UserResponseDTO apply(User user) {
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getFamilyName(),
                user.getName(),
                user.getMiddleName(),
                user.getRole().getId(),
                user.getStatus(),
                user.getCreatedAt());
    }
}
