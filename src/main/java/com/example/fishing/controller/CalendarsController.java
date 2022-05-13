package com.example.fishing.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.fishing.service.S3Wrapper;

@Controller
public class CalendarsController {
	
	@Autowired
	S3Wrapper s3;
	
	@GetMapping(path =  "/calendars")
	public String index(Model model) throws IOException {
		model.addAttribute("hasSidebar", true);
		ResponseEntity<byte[]> entity = s3.download("tags");
		String body = new String(entity.getBody());
		model.addAttribute("tags", body.split(System.getProperty("line.separator")));
		return "calendars/index";
	}

}