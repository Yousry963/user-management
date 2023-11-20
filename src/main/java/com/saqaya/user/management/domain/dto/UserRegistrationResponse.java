package com.saqaya.user.management.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {
    private String id;
    private String accessToken;
}
