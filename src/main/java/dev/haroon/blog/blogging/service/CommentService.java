package dev.haroon.blog.blogging.service;

import dev.haroon.blog.blogging.entities.Comment;
import dev.haroon.blog.blogging.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Integer postId);

	void deleteComment(Integer commentId);

	Comment dtoToComment(CommentDTO dto);

	CommentDTO commentToDTO(Comment comment);
}
