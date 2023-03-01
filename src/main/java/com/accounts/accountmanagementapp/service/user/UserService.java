package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(SaveUserRequestDTO saveUserRequestDTO);

    User editUser(EditUserRequestDTO editUserRequestDTO);

    User editPassword(EditPasswordRequestDTO editPasswordDTO);

    User editRole(EditRoleRequestDTO editRoleRequestDTO);

    User editStatus(String status);

    void deleteUser(UUID id);

    List<User> getUsers();

    User getUser(UUID id);
}
