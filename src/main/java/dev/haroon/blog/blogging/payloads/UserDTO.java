package dev.haroon.blog.blogging.payloads;



import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
//import dev.haroon.blog.blogging.entities.Role;

@Data
public class UserDTO {

	private Integer id;

	@NotEmpty
	@Size(min=4, message="Username must be minimum of 4 characters!")
	private String name;

	@Email(message="Email address is not valid!")
	private String email;

	@NotEmpty
	@Size(min=6, max=12, message="password must be min of 6 characters and max of 12 characters!")
	private String password;

	@NotEmpty
	private String about;
//	
//	@JsonIgnore
//	private Set<Role> roles;
}
