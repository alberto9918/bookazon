package com.santiagorodriguezalberto.bookazon.config

import io.swagger.models.Swagger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket{
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.santiagorodriguezalberto.bookazon"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
    }

    @Bean
    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API de Bookazon")
                .description("API de la app de Bookazon")
                .version("1.0")
                .contact(Contact("Alberto Santiago Rodríguez","","alberto@gmail.com"))
                .build()
    }
}