package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) throws UserHasAlreadyExistException;

    UserResponseDTO editUser(UUID id, EditUserRequestDTO editUserRequestDTO);

    UserResponseDTO editPassword(UUID id, EditPasswordRequestDTO editPasswordDTO);

    UserResponseDTO editRole(UUID id, EditRoleRequestDTO editRoleRequestDTO);

    UserResponseDTO editStatus(UUID id, String status);

    void deleteUser(UUID id);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUser(UUID id);
}
