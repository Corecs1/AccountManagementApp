package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO);

    UserResponseDTO editUser(EditUserRequestDTO editUserRequestDTO);

    UserResponseDTO editPassword(EditPasswordRequestDTO editPasswordDTO);

    UserResponseDTO editRole(EditRoleRequestDTO editRoleRequestDTO);

    UserResponseDTO editStatus(String status);

    void deleteUser(UUID id);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUser(UUID id);
}
