package com.accounts.accountmanagementapp.service.user;

import com.accounts.accountmanagementapp.dto.mapper.RequestDTOMapper;
import com.accounts.accountmanagementapp.dto.mapper.ResponseDTOMapper;
import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

// TODO Допилить все эндпоинты
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

        if (userExist(saveUserRequestDTO.getEmail())) {
            throw new UserHasAlreadyExistException("Пользователь с данным email уже существует");
        }

        Role role = roleRepository.getRoleById(saveUserRequestDTO.getRole());
        User user = requestDTOMapper.apply(saveUserRequestDTO, role, passwordEncoder);
        User savedUser = userRepository.save(user);

        return responseDTOMapper.apply(savedUser);
    }

    @Override
    public UserResponseDTO editUser(UUID id, @Valid EditUserRequestDTO editUserRequestDTO) {
        Optional<User> user = userRepository.findById(id);
        User updatedUser = requestDTOMapper.apply(editUserRequestDTO, user.get());
        userRepository.save(updatedUser);
        return responseDTOMapper.apply(updatedUser);
    }

    @Override
    public UserResponseDTO editPassword(UUID id, @Valid EditPasswordRequestDTO editPasswordRequestDTO) {
        if (editPasswordRequestDTO.getPassword().equals(editPasswordRequestDTO.getConfirmPassword())) {
            Optional<User> user = userRepository.findById(id);
            User updatedUser = user.get();
            updatedUser.setPassword(passwordEncoder.encode(editPasswordRequestDTO.getPassword()));
            userRepository.save(updatedUser);
            return responseDTOMapper.apply(updatedUser);
        }
        return null;
    }

    @Override
    public UserResponseDTO editRole(UUID id, @Valid EditRoleRequestDTO editRoleRequestDTO) {
        Role role = roleRepository.getRoleById(editRoleRequestDTO.getRole());
        Optional<User> user = userRepository.findById(id);
        User updatedUser = user.get();
        updatedUser.setRole(role);
        userRepository.save(updatedUser);
        return responseDTOMapper.apply(updatedUser);
    }

    @Override
    public UserResponseDTO editStatus(UUID id, String status) {
        Optional<User> user = userRepository.findById(id);
        if (status.equals("active")) {
            user.get().setStatus(Status.ACTIVE);
        } else if (status.equals("blocked")) {
            user.get().setStatus(Status.BLOCKED);
        }
        return responseDTOMapper.apply(userRepository.save(user.get()));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(responseDTOMapper::apply).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUser(UUID id) {
        return responseDTOMapper.apply(userRepository.findById(id).get());
    }

    //TODO Обработать по нормальному
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return null;
    }

    private boolean userExist(String email) {
        return userRepository.getUserByEmail(email).isPresent();
    }
}
