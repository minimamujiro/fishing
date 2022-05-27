package com.example.fishing.form;

import com.example.fishing.entity.User;

import lombok.Data;

@Data
public class WeatherForm {
	
	private Long Id;
	
	private String city_code;
	
	private String cityName;
	
	private String weather_city;
	
	private String weather_lat;
	
	private String weather_lon;
	
	private User user;

}
