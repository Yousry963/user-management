package com.saqaya.user.management.service;

import com.saqaya.user.management.common.exception.SystemException;
import com.saqaya.user.management.domain.dto.UserDto;
import com.saqaya.user.management.domain.dto.UserRegistrationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUserService extends UserDetailsService {

    public UserRegistrationResponse registerUser(UserDto userDto);

    public UserDto getUserById(String id) throws SystemException;
}
