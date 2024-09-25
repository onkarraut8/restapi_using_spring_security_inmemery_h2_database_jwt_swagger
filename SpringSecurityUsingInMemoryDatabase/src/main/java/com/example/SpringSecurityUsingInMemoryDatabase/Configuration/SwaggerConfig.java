package com.example.SpringSecurityUsingInMemoryDatabase.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@EnableWebMvc

public class SwaggerConfig {

	/*@Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api-v1")
                .pathsToMatch("/**") // Adjust this to match your API paths
                .build();
    }*/
	
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Documentation")
                .version("1.0")
                .description("API for Spring Boot with JWT and Spring Security"));
    }
    
    
}
