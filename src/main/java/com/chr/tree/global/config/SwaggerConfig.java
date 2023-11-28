package com.chr.tree.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "CHR",
                description = "CHR API명세",
                version = "1.0.0"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String paths = "/**";

        return GroupedOpenApi.builder()
                .group("CHR API 1.0.0")
                .pathsToMatch(paths)
                .build();
    }
}
