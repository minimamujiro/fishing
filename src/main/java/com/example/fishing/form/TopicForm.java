package com.example.fishing.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import com.example.fishing.validation.constraints.ImageByte;
import com.example.fishing.validation.constraints.ImageNotEmpty;

import lombok.Data;

@Data
public class TopicForm {

	private Long id;

	private Long userId;

	@ImageNotEmpty
	@ImageByte(max = 10000000)
	private MultipartFile image;

	private String imageData;

	private String path;

	@NotEmpty
	@Size(max = 1000)
	private String description;

	private Double lattitude;

	private Double longitude;

	private DateTime start_time;

	private DateTime end_time;

	private UserForm user;



}