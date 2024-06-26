//package dev.haroon.blog.blogging.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@Configuration
//@EnableWebSecurity
//@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//	
//	
//	private static final String[] PUBLIC_URLS = {
//			"/api/users/new", 
//			"/api/v1/auth/login",
//			"/v3/api-docs",
//			"/v3/api-docs",
//			"/swagger-resources/**",
//			"/swagger-ui/**",
//			"/webjars/**"
//	};
//	
//
//	
//	@Autowired
//	private AuthenticationProvider authProvider;
//
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Bean
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//		http.csrf()
//		    .disable()
//		    .authorizeHttpRequests()
//		    .antMatchers(PUBLIC_URLS)
//		    .permitAll()
//		    .antMatchers(HttpMethod.GET)
//		    .permitAll()
//		    .anyRequest()
//		    .authenticated()
//		    .and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.authenticationProvider(authProvider)
//			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}
//}
