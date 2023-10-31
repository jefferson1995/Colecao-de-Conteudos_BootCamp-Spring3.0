package com.devsuperior.dsmovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2 //Habilita o swagger no projeto
public class SwaggerConfig {

    //---------------------------------------------------
    /*
        Configuração para conseguir acessar os endpoints passando um token válido
     */
    private static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private List<SecurityContext> securityContext(){
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReference()).build());
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
    }

    //-----------------------------------------------------

    /*
        //linha 22: Posso utilizar o any() para colocar todos, mas o melhor é mencionar a pasta
            exemplo: .apis(RequestHandlerSelectors.basePackage("com.devsuperior.dsmovie.controllers"))
        //linha 22: decisão par asar todas classes que tem o restcontroller
     */


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(securityContext())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("Dsmovie API")
                .description("\"Spring Boot REST API for SDS 8\"").version("1.0.0")
                .contact(new Contact("GiHub", "https://github.com/jefferson1995", "https://www.linkedin.com/in/jefferson-barbosa-225349149/"))
                .build();
    }


    //Precisa colocar o Bearer e colar token na frente para autenticar no swagger

}
