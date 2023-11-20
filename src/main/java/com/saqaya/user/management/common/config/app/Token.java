package com.saqaya.user.management.common.config.app;

import lombok.Data;

@Data
public class Token {
    private String jwtSecret;
    private long jwtExpirationMs;
    private String bearerHeaderPrefix;
}
