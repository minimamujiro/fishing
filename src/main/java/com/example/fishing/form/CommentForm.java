package com.example.fishing.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentForm {
	
	private Long id;
	
	private Long topicId;
	
	@NotEmpty
	@Size(max = 1000)
	private String description;

}