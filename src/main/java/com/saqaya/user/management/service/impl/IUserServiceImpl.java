package com.saqaya.user.management.service.impl;

import com.saqaya.user.management.common.exception.SystemException;
import com.saqaya.user.management.common.exception.ErrorResponse;
import com.saqaya.user.management.common.config.util.JwtUtils;
import com.saqaya.user.management.domain.dto.UserDto;
import com.saqaya.user.management.domain.entities.User;
import com.saqaya.user.management.domain.dto.UserRegistrationResponse;
import com.saqaya.user.management.repository.UserRepository;
import com.saqaya.user.management.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public
class IUserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @Override
    public UserRegistrationResponse registerUser(UserDto userDto) {

        userDto.idGenerator();// generate the hash id

        User user = userRepository.save(new User(userDto));// Save the entity

        return UserRegistrationResponse.builder()
                .id(user.getId())
                .accessToken(jwtUtils.generateJwtToken(user.getEmail())) // generate the access token
                .build();
    }

    public UserDto getUserById(String id) throws SystemException {
        User user = userRepository.findById(id).orElseThrow(() -> new SystemException(ErrorResponse.RESOURCE_NOT_FOUND));
        return new UserDto(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findTopByEmail(username).orElseThrow(() -> new UsernameNotFoundException(""));

        return new UserDto(user);
    }
}
