package com.example.Online_Course_Management_Work.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI onlineCourseOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Online Course Management API")
                .description("REST API for course, enrollment, user and material management")
                .version("v1")
                .contact(new Contact().name("API Support").email("support@example.com")));
    }
}

