package com.saqaya.user.management.controller;

import com.saqaya.user.management.common.exception.SystemException;
import com.saqaya.user.management.domain.dto.UserDto;
import com.saqaya.user.management.domain.dto.UserRegistrationResponse;
import com.saqaya.user.management.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class UserController implements UserApi {

    private IUserService iUserService;

    @Override
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid UserDto userDto) {
        return ResponseEntity.ok(iUserService.registerUser(userDto));
    }


    @Override
    public ResponseEntity<UserDto> getUserById(String id) throws SystemException {
        UserDto dto = iUserService.getUserById(id);
        return ResponseEntity.ok(dto);
    }

}
