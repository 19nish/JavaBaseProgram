package com.springboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private long id;
	
	@NotEmpty(message = "Name should not be empty")
	private String name;
	
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 10, message= "Body should not be empty it should have 10 charachters")
	private String body;

}
