package br.com.cabral.basic_api.configuration.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Exemplo Api SpringBoot")
                        .version("1.0")
                        .description("Está é uma api base para criar outras apis")
                );
    }
}
