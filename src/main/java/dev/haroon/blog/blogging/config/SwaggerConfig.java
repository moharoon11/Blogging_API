//package dev.haroon.blog.blogging.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//
//	
//	public static final String AUTHORIZATION_HEADER = "Authorization";
//	
//	private ApiKey apiKeys() {
//		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//	}
//	
//	private List<SecurityContext> securityContext() {
//		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
//	}
//	
//	private List<SecurityReference> securityReferences() {
//		AuthorizationScope scope = new AuthorizationScope("gloabl", "accessEverything");
//		return Arrays.asList(new SecurityReference("jwt", new AuthorizationScope[] {scope}));
//	}
//	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(getAppInfo())
//			    .securityContexts(securityContext())
//			    .securitySchemes(Arrays.asList(apiKeys()))
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}
//
//	private ApiInfo getAppInfo() {
//
//		return new ApiInfo("Backend Blogging Application", "This Project is developed by Mohammed Haroon", "1.0",
//				"Terms of Service", new Contact("Mohammed Haroon", null, "mohd.coding7@gmail.com"), "License of Apis",
//				"Api Licence URL", Collections.EMPTY_LIST);
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//
//	}
//
//}
