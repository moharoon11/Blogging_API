package dev.haroon.blog.blogging.service;

import java.util.List;

import dev.haroon.blog.blogging.entities.*;
import dev.haroon.blog.blogging.payloads.UserDTO;

public interface UserService {

	UserDTO registerNewUser(UserDTO user);

	UserDTO findById(Integer id);

	UserDTO update(UserDTO user, Integer id);

	void delete(Integer id);
	
	UserDTO findByEmail(String name);

	List<UserDTO> getUsers();
	
	UserDTO userToDTO(User user);
	
	User dtoToUser(UserDTO dto);
	
	

}
