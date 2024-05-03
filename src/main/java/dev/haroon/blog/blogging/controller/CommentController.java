package dev.haroon.blog.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haroon.blog.blogging.payloads.ApiResponse;
import dev.haroon.blog.blogging.payloads.CommentDTO;
import dev.haroon.blog.blogging.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
			                                        @PathVariable Integer postId) {
		
		return ResponseEntity.ok(commentService.createComment(commentDTO, postId));
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("Comment Deleted Successfully", true));
	}
	
	
	
	

}
