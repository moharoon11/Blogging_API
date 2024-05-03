package dev.haroon.blog.blogging.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;


public class CommentDTO {

	
	private Integer commentId;

	@NotEmpty
	@Size(min=3, message="comment must be minimum of 3 characters!")
	private String content;
	
	

	public Integer getCommentId() {
		return commentId;
	}



	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", content=" + content + "]";
	}
	
	
	
	
}
