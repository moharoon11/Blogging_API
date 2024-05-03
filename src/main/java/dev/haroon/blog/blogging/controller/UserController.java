package dev.haroon.blog.blogging.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import dev.haroon.blog.blogging.entities.Role;
import dev.haroon.blog.blogging.payloads.ApiResponse;
import dev.haroon.blog.blogging.payloads.UserDTO;
//import dev.haroon.blog.blogging.repositories.RoleRepository;
import dev.haroon.blog.blogging.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private RoleRepository roleRepo;
	
	@PostMapping("/new")
	public ResponseEntity<UserDTO> newRegister(@Valid @RequestBody UserDTO userDTO) {
		
//		String hashPw = passwordEncoder.encode(userDTO.getPassword());
//		userDTO.setPassword(hashPw);
	
		return ResponseEntity.ok(userService.registerNewUser(userDTO));
	}
	

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, 
			                                  @PathVariable Integer userId) {
		
//		String passwrod = userDTO.getPassword();
//		String hashPw = passwordEncoder.encode(passwrod);
//		userDTO.setPassword(hashPw);
		return ResponseEntity.ok(userService.update(userDTO, userId));
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
//	// ADMIN ONLY ACCESS
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.delete(userId);
		return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", true));
	}
	
	
	
	
	
	
}
