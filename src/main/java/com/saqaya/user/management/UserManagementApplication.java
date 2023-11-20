package com.saqaya.user.management;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Configuration
    public class OpenAPI30Configuration {
        @Bean
        public OpenAPI customizeOpenAPI() {
            return new OpenAPI()
                    .info(new Info().title("User Management Documentation")
                            .version("v1.0.0"));
        }
    }
}
