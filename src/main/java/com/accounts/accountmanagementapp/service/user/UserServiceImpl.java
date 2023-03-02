package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.repository.RoleRepository;
import com.accounts.accountmanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO editUser(EditUserRequestDTO editUserRequestDTO) {
//        User user = userRepository.getByEmail(editUserRequestDTO.getEmail());
//        user.setEmail();
        return null;
    }

    @Override
    public UserResponseDTO editPassword(EditPasswordRequestDTO editPasswordDTO) {
        return null;
    }

    @Override
    public UserResponseDTO editRole(EditRoleRequestDTO editRoleRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO editStatus(String status) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return null;
    }

    @Override
    public UserResponseDTO getUser(UUID id) {
        return null;
    }
}
