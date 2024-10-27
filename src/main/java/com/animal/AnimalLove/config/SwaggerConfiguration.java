package com.animal.AnimalLove.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("애니멀러브(AnimalLove) SNS API")
                        .version("1.0")
                        .description("This is a custom API description")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("라이센스").url("http://springdoc.org"))
                        .contact(new Contact().name("동물사랑꾼").email("animalLove@naver.com")));
    }

}
