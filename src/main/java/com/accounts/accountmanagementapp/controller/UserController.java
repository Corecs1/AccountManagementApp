package com.accounts.accountmanagementapp.controller;

import com.accounts.accountmanagementapp.dto.requestDto.SaveUserRequestDTO;
import com.accounts.accountmanagementapp.dto.responseDto.UserResponseDTO;
import com.accounts.accountmanagementapp.exception.UserHasAlreadyExistException;
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

    // TODO Тут надо выбрасывать response с ошибкой для клиента
    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        UserResponseDTO userResponseDTO = null;
        try {
            userResponseDTO = userService.saveUser(saveUserRequestDTO);
        } catch (UserHasAlreadyExistException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
