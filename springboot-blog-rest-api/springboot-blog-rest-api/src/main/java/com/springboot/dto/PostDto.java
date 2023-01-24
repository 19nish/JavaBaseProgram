package com.springboot.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {
	private long id;
	
	@NotEmpty
	@Size(min=2, message="post title should have at least two charachters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message="post descrption should be atleast of 10 charchters")
	private String description;
	
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;

}
