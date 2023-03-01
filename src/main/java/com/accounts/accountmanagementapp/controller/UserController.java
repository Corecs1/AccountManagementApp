package com.accounts.accountmanagementapp.controller;

import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        UserResponseDTO userResponseDTO = userService.saveUser(saveUserRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
