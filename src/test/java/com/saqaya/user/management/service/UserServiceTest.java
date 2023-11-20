package com.saqaya.user.management.service;
import com.saqaya.user.management.common.config.util.JwtUtils;
import com.saqaya.user.management.common.exception.SystemException;
import com.saqaya.user.management.domain.dto.UserDto;
import com.saqaya.user.management.domain.dto.UserRegistrationResponse;
import com.saqaya.user.management.domain.entities.User;
import com.saqaya.user.management.repository.UserRepository;
import com.saqaya.user.management.service.impl.IUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class IUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private IUserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        // Given
        UserDto userDto = new UserDto();
        when(jwtUtils.generateJwtToken(anyString())).thenReturn("mockedAccessToken");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // When
        UserRegistrationResponse response = userService.registerUser(userDto);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
        // Add assertions for the response if needed
    }

    @Test
    void getUserById() throws SystemException {
        // Given
        String userId = "mockedUserId";
        when(userRepository.findById(anyString())).thenReturn(java.util.Optional.of(new User()));

        // When
        userService.getUserById(userId);

        // Then
        verify(userRepository, times(1)).findById(anyString());
        // Add assertions or additional verifications if needed
    }
}
