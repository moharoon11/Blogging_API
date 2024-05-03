package dev.haroon.blog.blogging.service;

import java.util.Set;

import dev.haroon.blog.blogging.entities.Post;
import dev.haroon.blog.blogging.payloads.CategoryDTO;
import dev.haroon.blog.blogging.payloads.PostDTO;
import dev.haroon.blog.blogging.payloads.PostResponse;
import dev.haroon.blog.blogging.payloads.UserDTO;

public interface PostService {

	
	Set<PostDTO> findByUser(Integer userId);
	
	Set<PostDTO> findByCategory(Integer categoryId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

	PostDTO findById(Integer postId);

	PostDTO update(PostDTO postDTO, Integer postId);

	void delete(Integer postId);
	
	PostDTO postToDTO(Post post);
	
	Post dtoToPost(PostDTO postDTO);
	
	Set<PostDTO> searchByTitle(String title);
	
	
}
