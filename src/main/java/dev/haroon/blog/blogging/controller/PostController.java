package dev.haroon.blog.blogging.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.haroon.blog.blogging.payloads.ApiResponse;
import dev.haroon.blog.blogging.payloads.PostDTO;
import dev.haroon.blog.blogging.payloads.PostResponse;
import dev.haroon.blog.blogging.service.FileService;
import dev.haroon.blog.blogging.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	

	
	

	
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project-image}")
	private String path;

	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId /*@RequestParam("file") MultipartFile file*/) {

		
		
		return ResponseEntity.ok(postService.createPost(postDTO, userId, categoryId));

	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		return ResponseEntity.ok(postService.findById(postId));
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
		return ResponseEntity.ok(postService.update(postDTO, postId));
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		postService.delete(postId);
		return ResponseEntity.ok(new ApiResponse("Post Deleted Successfully", true));
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<Set<PostDTO>> getPostByUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(postService.findByUser(userId));
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<Set<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(postService.findByCategory(categoryId));
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAll(
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
		return ResponseEntity.ok(postService.getAllPost(pageNumber, pageSize, sortBy, sortDir));
	}

	@GetMapping("/posts/search/{title}")
	public ResponseEntity<Set<PostDTO>> getPostsByKeyword(@PathVariable String title) {
		return ResponseEntity.ok(postService.searchByTitle(title));
	}

	@PostMapping("/posts/{postId}/upload/file")
	public ResponseEntity<PostDTO> uploadFile(@PathVariable Integer postId, @RequestParam("file") MultipartFile file)
			throws IOException {

		PostDTO postDTO = postService.findById(postId);

		String fileName = fileService.uploadFile(path, file);
		postDTO.setImageName(fileName);

		postDTO = postService.update(postDTO, postId);

		return ResponseEntity.ok(postDTO);

	}

	@GetMapping(value = "/posts/download/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {

		InputStream inputStream = fileService.getResource(path, fileName);

		StreamUtils.copy(inputStream, response.getOutputStream());
	}

}
