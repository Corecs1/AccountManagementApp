package com.accounts.accountmanagementapp.controller;

import com.accounts.accountmanagementapp.dto.requestDto.EditPasswordRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditRoleRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.EditUserRequestDTO;
import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
import com.accounts.accountmanagementapp.exception.WrongValueStatusException;
import com.accounts.accountmanagementapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody SaveUserRequestDTO saveUserRequestDTO) throws UserHasAlreadyExistException {
        return new ResponseEntity<>(userService.saveUser(saveUserRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> editUser(@PathVariable UUID id, @RequestBody EditUserRequestDTO editUserRequestDTO) throws UserHasAlreadyExistException {
        return new ResponseEntity<>(userService.editUser(id, editUserRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/{id}/set-password")
    public ResponseEntity<UserResponseDTO> editPassword(@PathVariable UUID id, @RequestBody EditPasswordRequestDTO editPasswordRequestDTO) {
        return new ResponseEntity<>(userService.editPassword(id, editPasswordRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/{id}/set-role")
    public ResponseEntity<UserResponseDTO> editRole(@PathVariable UUID id, @RequestBody EditRoleRequestDTO editRoleRequestDTO) {
        return new ResponseEntity<>(userService.editRole(id, editRoleRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/{id}/{state}")
    public ResponseEntity<UserResponseDTO> editStatus(@PathVariable UUID id, @PathVariable String state) throws WrongValueStatusException {
        return new ResponseEntity<>(userService.editStatus(id, state), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
