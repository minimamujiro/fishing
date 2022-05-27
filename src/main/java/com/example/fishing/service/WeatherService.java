package com.example.fishing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fishing.entity.Weather;
import com.example.fishing.repository.WeatherRepository;

@Service
@Transactional
public class WeatherService {

	@Autowired
	WeatherRepository repository;
	
	public List<Weather> selectcityName(String live) {
		return repository.findByCityName(live);
	}
	
}