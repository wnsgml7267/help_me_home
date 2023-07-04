package com.ssafy.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Spring에게 아래 클래스는 설정 클래스라고 알려줌
@MapperScan(basePackages = {"com.ssafy.**.mapper"})  // MyBatis Mapper 스캔
@EnableAspectJAutoProxy  // AOP ProxyBeanFactory 생성 (AOP 사용하도록 설정)
public class WebMvcConfiguration implements WebMvcConfigurer {

//	private final List<String> patterns = Arrays.asList("/article/*", "/admin", "/user/list");

	@Value("${file.path}")
	private String uploadPath;

	public WebMvcConfiguration() {}
	//	@Override
	//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//		registry.addResourceHandler("/upload/image/**").addResourceLocations("file://c:/aaa/aa");
	//	}


	// CORS 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		//.allowedOrigins("http://www.ssafy.com", "http://localhost", "http://localhost:8080")
		.allowedMethods(
				HttpMethod.GET.name(), 
				HttpMethod.POST.name(), 
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name(), 
				HttpMethod.HEAD.name(), 
				HttpMethod.OPTIONS.name(),
				HttpMethod.PATCH.name())
		.maxAge(1800);
	}
}
