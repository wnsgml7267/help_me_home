package com.ssafy.config;

import java.util.HashSet;
import java.util.Set;

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

@Configuration
@EnableSwagger2  // http://localhost:8080/{우리 서버 주소}/swagger-ui.html (2.0버전)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		final ApiInfo apiInfo = new ApiInfoBuilder()
				.title("SSAFY HOME")
				.description("<h3>데일리 실습에서 사용되는 RestApi에 대한 문서를 제공한다.</h3>")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("MIT License")
				.version("1.0")
				.build();
		
		return new Docket(DocumentationType.SWAGGER_2)  // Swagger 2.0 기반의 문서 작성
				.consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes())
				.apiInfo(apiInfo)						// 문서에 대한 정보를 설정
				.select()								// ApiSelectorBuilder를 반환하며, 상세한 설정 처리
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.home.controller"))  // 대상으로 하는 api 설정
//				.paths(PathSelectors.ant("/*api/**"))  // Controller에서 Swagger를 지정할 대상 path 설정
				.build()
				.useDefaultResponseMessages(false);
	}
	
	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		//      consumes.add("application/xml;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}
}
