package br.com.cabral.basic_api.configuration.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi(){

        String SchemaName = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement().addList(SchemaName)
                )
                .info(new Info()
                        .title("Exemplo Api SpringBoot")
                        .version("1.0")
                        .description("Está é uma api base para criar outras apis")
                ).components(
                        new Components()
                                .addSecuritySchemes(SchemaName,
                                            new SecurityScheme().name("Authorization")
                                                    .type(SecurityScheme.Type.HTTP)
                                                    .scheme("bearer")
                                                    .bearerFormat("JWT")

                                        )
                );
    }
}
