package com.devsuperior.dsmovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //Habilita o swagger no projeto
public class SwaggerConfig {

    /*
        //linha 22: Posso utilizar o any() para colocar todos, mas o melhor é mencionar a pasta
            exemplo: .apis(RequestHandlerSelectors.basePackage("com.devsuperior.dsmovie.controllers"))
        //linha 22: decisão par asar todas classes que tem o restcontroller
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())  //posso adicionar uma rota especifica tbm
                .build();
    }

}
