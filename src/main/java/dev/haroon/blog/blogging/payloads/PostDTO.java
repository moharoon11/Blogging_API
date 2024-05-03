package dev.haroon.blog.blogging.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDTO {
	
	private Integer postId;
	
	private String title;
	
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDTO categoryDTO;
	
	private UserDTO userDTO;

}
