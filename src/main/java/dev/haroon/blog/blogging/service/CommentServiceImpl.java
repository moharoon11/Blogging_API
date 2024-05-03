package dev.haroon.blog.blogging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.haroon.blog.blogging.exceptions.*;
import dev.haroon.blog.blogging.entities.*;
import dev.haroon.blog.blogging.payloads.*;
import dev.haroon.blog.blogging.repositories.*;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "POST ID", postId));

		Comment comment = dtoToComment(commentDTO);
		comment.setPost(post);

		comment = commentRepo.save(comment);

		commentDTO = commentToDTO(comment);

		return commentDTO;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("COMMENT", "COMMENT ID", commentId));

		commentRepo.deleteById(comment.getCommentId());

	}



	@Override
	public Comment dtoToComment(CommentDTO dto) {
		Comment comment = new Comment();
		comment.setCommentId(dto.getCommentId());
		comment.setContent(dto.getContent());
		return comment;
	}

	@Override
	public CommentDTO commentToDTO(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setCommentId(comment.getCommentId());
		dto.setContent(comment.getContent());
		return dto;
	}

}
