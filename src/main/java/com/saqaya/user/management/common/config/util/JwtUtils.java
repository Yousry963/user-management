package com.saqaya.user.management.common.config.util;

import com.saqaya.user.management.common.config.app.ApplicationConfiguration;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@Slf4j
@AllArgsConstructor
public class JwtUtils {

    private ApplicationConfiguration config;

    public String generateJwtToken(String userName) {

        return Jwts.builder().setSubject(userName)
                .setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + config.getToken().getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, config.getToken().getJwtSecret()).compact();
    }

    public String getUserName(HttpServletRequest request) {

        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(config.getToken().getBearerHeaderPrefix()))
            return getUserNameFromJWTToken(headerAuth.substring(7, headerAuth.length()));

        return null;
    }

    public String getUserNameFromJWTToken(String token) {
        return Jwts.parser().setSigningKey(config.getToken().getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(config.getToken().getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


}
