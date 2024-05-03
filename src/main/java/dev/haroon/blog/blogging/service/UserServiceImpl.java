package dev.haroon.blog.blogging.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import dev.haroon.blog.blogging.entities.*;
import dev.haroon.blog.blogging.exceptions.*;
import dev.haroon.blog.blogging.entities.*;
import dev.haroon.blog.blogging.payloads.*;
import dev.haroon.blog.blogging.repositories.*;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

//	@Autowired
//	private RoleRepository roleRepo;


	public UserDTO findById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", id));

		UserDTO userDTO = userToDTO(user);
//		userDTO.setRoles(user.getRoles());

		return userDTO;

	}

	public UserDTO update(UserDTO userDTO, Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", id));

//		userDTO.setRoles(user.getRoles());
		userDTO.setId(id);

		user = dtoToUser(userDTO);
//		user.setRoles(userDTO.getRoles());

		User updatedUser = userRepo.save(user);

		UserDTO updatedDTO = userToDTO(updatedUser);
//		updatedDTO.setRoles(updatedUser.getRoles());

		return updatedDTO;

	}

	public void delete(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", id));
		userRepo.delete(user);
	}

	public List<UserDTO> getUsers() {
		List<User> users = userRepo.findAll();

		List<UserDTO> usersDTO = new ArrayList<>();

		for (User user : users) {
			UserDTO userDTO = userToDTO(user);
//			userDTO.setRoles(user.getRoles());
			usersDTO.add(userDTO);
		}

		if (usersDTO.size() == 0)
			throw new NoResourceFoundException("USER", "Ooops!!! No Records found for ");

		return usersDTO;
	}

	public UserDTO userToDTO(User user) {
		UserDTO dto = new UserDTO();

		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());

		return dto;
	}

	public User dtoToUser(UserDTO dto) {
		User user = new User();

		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());

		return user;
	}

	@Override
	public UserDTO findByEmail(String name) {
		UserDTO dto = null;
		Optional<User> entity = userRepo.findByEmail(name);

		if (entity.isPresent())
			dto = userToDTO(entity.get());
		else
			throw new ResourceNotFoundException("USER", "EMAIL", name);

		return dto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO dto) {

		User user = dtoToUser(dto);
//
//		Role role = roleRepo.findById(500).get();

//		Set<Role> roles = user.getRoles();
//		roles.add(role);
//
//		user.setRoles(roles);

		User savedUser = userRepo.save(user);

		UserDTO userDTO = userToDTO(savedUser);
//		userDTO.setRoles(roles);

		return userDTO;
	}
}