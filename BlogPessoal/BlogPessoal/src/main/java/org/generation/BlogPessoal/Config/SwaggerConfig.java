package org.generation.BlogPessoal.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //se trata de um swagger entao temos que colocar a anotação
@Configuration //colocamos anotação de configuração para q o programa saiba doq se trata
public class SwaggerConfig {

	@Bean //dizendo pro Spring que quer criar esse objeto e deixar ele disponível para outras classes utilizarem ele como dependência
	public Docket docket () { //cria documentação automatica
		return new Docket (DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.generation.BlogPessoal.Controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo()); //TERMINAR AQUII
	}
	
		private ApiInfo apiInfo() {
			return new ApiInfoBuilder()
					.title("Blog Pessoal")
					.description("API do Projeto de blog pessoal")
					.version("1.0")
					.contact(contact())
					.build();
		}
		
		private Contact contact () {
			return new Contact("Jaqueline Cavalaro", 
					"https://github.com/JaquelineCavalaro", 
					"Desenvolvedora Java Jr. Full Stack");
					
		}
}
