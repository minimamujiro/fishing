package com.example.fishing.form;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
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
	
	private Double latitude;

	private Double longitude;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@NotEmpty
	@Size(max = 10)
	private String weather;
	
	@NotEmpty
	@Size(max = 2)
	private String tide_level;
    
	private LocalTime start_time;

	private LocalTime end_time;

	private UserForm user;
	
	private List<FavoriteForm> favorites;
	
	private FavoriteForm favorite;
	
	private List<CommentForm> comments;

}