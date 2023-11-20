package com.saqaya.user.management.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

	private String token;
	private String refreshToken;
	@Builder.Default
	private String type = "Bearer";
	private String username;
	private String email;
	private Boolean tempPassword;

}
