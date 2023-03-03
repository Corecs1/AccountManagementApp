package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.mapper.UserResponseDTOMapper;
import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.model.Role;
import com.accounts.accountmanagementapp.model.Status;
import com.accounts.accountmanagementapp.model.User;
import com.accounts.accountmanagementapp.repository.RoleRepository;
import com.accounts.accountmanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final UserResponseDTOMapper userResponseDTOMapper;

    // TODO Переделать под лямбду
    @Override
    public UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) {
        User user = userRepository.getUserByEmail(saveUserRequestDTO.getEmail());

        // TODO Создать exception UserHasAlreadyExist и выбросить исключенеие
        if (user != null) {
            return null;
        }

        Role role = roleRepository.getRoleById(saveUserRequestDTO.getRole());

        user = User.builder()
                .email(saveUserRequestDTO.getEmail())
                .password(passwordEncoder.encode(saveUserRequestDTO.getPassword()))
                .name(saveUserRequestDTO.getName())
                .familyName(saveUserRequestDTO.getFamilyName())
                .middleName(saveUserRequestDTO.getMiddleName())
                .role(role)
                .status(Status.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        return userResponseDTOMapper.apply(savedUser);
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

    //TODO Обработать по нормальному
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return null;
    }
}
