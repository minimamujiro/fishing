package com.example.fishing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name =  "city_data")
@Data
public class Weather {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String city_code;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column
	private String weather_city;
	
	@Column
	private String weather_lat;
	
	@Column
	private String weather_lon;
	
}
