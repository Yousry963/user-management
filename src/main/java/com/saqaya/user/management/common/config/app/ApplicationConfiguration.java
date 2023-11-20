package com.saqaya.user.management.common.config.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {

	private Token token;
	private Urls urls;

}
