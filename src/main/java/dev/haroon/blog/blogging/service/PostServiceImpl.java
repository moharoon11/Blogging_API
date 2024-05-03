package dev.haroon.blog.blogging.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.haroon.blog.blogging.exceptions.*;

import dev.haroon.blog.blogging.entities.*;


import dev.haroon.blog.blogging.payloads.*;
import dev.haroon.blog.blogging.repositories.*;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Set<PostDTO> findByUser(Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

		Set<PostDTO> postDTOList = new HashSet<PostDTO>();

		postRepo.findByUser(user).stream().forEach(post -> {
			PostDTO postDTO = postToDTO(post);
			postDTOList.add(postDTO);
		});

		return postDTOList;
	}

	@Override
	public Set<PostDTO> findByCategory(Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "ID", categoryId));

		Set<PostDTO> postDTOList = new HashSet<PostDTO>();

		postRepo.findByCategory(category).stream().forEach(post -> {
			PostDTO postDTO = postToDTO(post);
			postDTOList.add(postDTO);
		});

		return postDTOList;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc"))
			sort = Sort.by(Direction.ASC, sortBy);
		else
			sort = Sort.by(Direction.DESC, sortBy);

		Set<PostDTO> postDTOList = new HashSet<PostDTO>();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = postRepo.findAll(pageable);
		

		pagePost.getContent().forEach(post -> {
			postDTOList.add(postToDTO(post));
		});

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDTOList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "ID", categoryId));

		UserDTO userDTO = userService.userToDTO(user);
//		userDTO.setRoles(user.getRoles());

		postDTO.setUserDTO(userDTO);
		postDTO.setCategoryDTO(categoryService.categoryToDTO(category));

		Post post = dtoToPost(postDTO);

		post = postRepo.save(post);

		return postToDTO(post);
	}

	@Override
	public PostDTO findById(Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));

		PostDTO postDTO = postToDTO(post);

		return postDTO;
	}

	@Override
	public PostDTO update(PostDTO postDTO, Integer postId) {

		// previous data from the database
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));

		postDTO.setPostId(post.getPostId());
		postDTO.setUserDTO(userService.userToDTO(post.getUser()));
		postDTO.setCategoryDTO(categoryService.categoryToDTO(post.getCategory()));

		post = dtoToPost(postDTO);

		// updated post after committing to database
		post = postRepo.save(post);

		return postToDTO(post);
	}

	@Override
	public void delete(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));

		postRepo.delete(post);
	}

	@Override
	public PostDTO postToDTO(Post post) {

		PostDTO dto = new PostDTO();
		dto.setPostId(post.getPostId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setImageName(post.getImageName());
		dto.setAddedDate(new Date());

		CategoryDTO categoryDTO = categoryService.categoryToDTO(post.getCategory());
		dto.setCategoryDTO(categoryDTO);

		UserDTO userDTO = userService.userToDTO(post.getUser());
		dto.setUserDTO(userDTO);

		return dto;
	}

	public Post dtoToPost(PostDTO dto) {
		Post post = new Post();

		post.setPostId(dto.getPostId());
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		post.setAddedDate(new Date());

		Category category = categoryService.dtoToCategory(dto.getCategoryDTO());
		post.setCategory(category);

		User user = userService.dtoToUser(dto.getUserDTO());
		post.setUser(user);

		return post;
	}

	@Override
	public Set<PostDTO> searchByTitle(String title) {

		Set<Post> posts = postRepo.findByTitleLike(title);

		Set<PostDTO> postsDTO = new HashSet<>();

		if (posts.isEmpty())
			throw new NoResourceFoundException("POST", "No Resource Available For " + title);
		else
			posts.forEach(post -> postsDTO.add(postToDTO(post)));

		return postsDTO;
	}

}
