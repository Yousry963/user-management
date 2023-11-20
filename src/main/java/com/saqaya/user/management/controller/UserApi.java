package com.saqaya.user.management.controller;

import com.saqaya.user.management.common.exception.SystemException;
import com.saqaya.user.management.domain.dto.UserRegistrationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.saqaya.user.management.domain.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Management operations")
public interface UserApi {
    @PostMapping("/user")
    @Operation(summary = "Register User")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserDto userDto);
    @GetMapping("/user/{id}")
    @Operation(summary = "Get User by id")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) throws SystemException;


}
