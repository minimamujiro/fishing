package com.example.fishing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishing.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, String> {

	List<Weather> findByCityName(String live);
}