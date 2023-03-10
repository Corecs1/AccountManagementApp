package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.mapper.RequestDTOMapper;
import com.accounts.accountmanagementapp.dto.mapper.ResponseDTOMapper;
import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.exception.PasswordMismatchException;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
import com.accounts.accountmanagementapp.exception.WrongValueStatusException;
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
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResponseDTOMapper responseDTOMapper;
    private final RequestDTOMapper requestDTOMapper;

    @Override
    public UserResponseDTO saveUser(@Valid SaveUserRequestDTO saveUserRequestDTO) throws UserHasAlreadyExistException {
        duplicateUserCheck(saveUserRequestDTO.getEmail());

        Role role = getRoleById(saveUserRequestDTO.getRole());
        User user = requestDTOMapper.apply(saveUserRequestDTO, role, passwordEncoder);
        User savedUser = userRepository.save(user);

        return responseDTOMapper.apply(savedUser);
    }

    @Override
    public UserResponseDTO editUser(UUID id, @Valid EditUserRequestDTO editUserRequestDTO) throws UserHasAlreadyExistException {
        User user = getUserById(id);
        if (!user.getEmail().equals(editUserRequestDTO.getEmail())) {
            duplicateUserCheck(editUserRequestDTO.getEmail());
        }
        User updatedUser = requestDTOMapper.apply(editUserRequestDTO, user);
        userRepository.save(updatedUser);
        return responseDTOMapper.apply(updatedUser);
    }

    @Override
    public UserResponseDTO editPassword(UUID id, @Valid EditPasswordRequestDTO editPasswordRequestDTO) {
        if (!editPasswordRequestDTO.getPassword().equals(editPasswordRequestDTO.getConfirmPassword())) {
            throw new PasswordMismatchException("?????????????????? ???????????? ???? ??????????????????");
        }
        User user = getUserById(id);
        user.setPassword(passwordEncoder.encode(editPasswordRequestDTO.getPassword()));
        userRepository.save(user);
        return responseDTOMapper.apply(user);
    }

    @Override
    public UserResponseDTO editRole(UUID id, @Valid EditRoleRequestDTO editRoleRequestDTO) {
        Role role = getRoleById(editRoleRequestDTO.getRole());
        User user = getUserById(id);
        user.setRole(role);
        userRepository.save(user);
        return responseDTOMapper.apply(user);
    }

    @Override
    public UserResponseDTO editStatus(UUID id, String status) throws WrongValueStatusException {
        User user = getUserById(id);
        if (status.equals("active")) {
            user.setStatus(Status.ACTIVE);
        } else if (status.equals("blocked")) {
            user.setStatus(Status.BLOCKED);
        } else {
            throw new WrongValueStatusException("?????????????? ???????????????????? ???????????????? ?????????????? (active/blocked)");
        }
        return responseDTOMapper.apply(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(responseDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUser(UUID id) {
        return responseDTOMapper.apply(getUserById(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("???????????????????????? ?? ?????????????????????????????? '[%s]' ???? ????????????", id)));
    }

    private Role getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("???????? ?? ?????????????????????????????? '[%s]' ???? ??????????????", id)));
    }

    private void duplicateUserCheck(String email) throws UserHasAlreadyExistException {
        if (userRepository.getUserByEmail(email).isPresent()) {
            throw new UserHasAlreadyExistException("???????????? ???????????????????????? ?? ?????????? e-mail ?????? ????????????????????");
        }
    }
}
