//package dev.haroon.blog.blogging.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import dev.haroon.blog.blogging.payloads.JwtAuthRequest;
//import dev.haroon.blog.blogging.payloads.JwtAuthResponse;
//import dev.haroon.blog.blogging.security.JwtService;
//
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthenticationController {
//	
//	
//	@Autowired
//	private AuthenticationManager authManager;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private JwtService jwtService;
//	
//	@PostMapping("/login")
//	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {
//		
//		authenticate(request.getUsername(), request.getPassword());
//		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//		
//		String token = jwtService.generateToken(userDetails);
//		
//		JwtAuthResponse authResponse = new JwtAuthResponse();
//		authResponse.setToken(token);
//		
//		return ResponseEntity.ok(authResponse);
//	}
//	
//	private void authenticate(String username, String password) {
//		
//		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//				                                        username, password);
//		
//		authManager.authenticate(authToken);
//	}
//	
//	
//	
//
//}
