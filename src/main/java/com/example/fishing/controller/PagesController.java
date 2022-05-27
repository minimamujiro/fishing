package com.example.fishing.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fishing.entity.User;
import com.example.fishing.entity.Weather;
import com.example.fishing.service.S3Wrapper;
import com.example.fishing.service.WeatherService;

@Controller
public class PagesController {
	
	@Autowired
	WeatherService service;

	@Autowired
	S3Wrapper s3;

	@RequestMapping("/")
	public String index(Model model) throws IOException {
		/*アフィリエイトの表示*/
		model.addAttribute("hasSidebar", true);
		ResponseEntity<byte[]> entity = s3.download("tags");
		String body = new String(entity.getBody());
		model.addAttribute("tags", body.split(System.getProperty("line.separator")));
		/*pages/indexに潮汐APIで使う値を渡す*/
		Date now = new Date();
		String year = new SimpleDateFormat("yyyy").format(now);
		String month = new SimpleDateFormat("M").format(now);
		String day = new SimpleDateFormat("d").format(now);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);

		return "pages/index";
	}

	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String index(Principal principal, Model model) throws IOException {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String live = user.getLive();
		List<Weather> i = getweatherlive(live);
		model.addAttribute("citydata", i);
		
		
		

		/*アフィリエイトの表示*/
		model.addAttribute("hasSidebar", true);
		ResponseEntity<byte[]> entity = s3.download("tags");
		String body = new String(entity.getBody());
		model.addAttribute("tags", body.split(System.getProperty("line.separator")));
		/*pages/indexに潮汐APIで使う値を渡す*/
		Date now = new Date();
		String year = new SimpleDateFormat("yyyy").format(now);
		String month = new SimpleDateFormat("M").format(now);
		String day = new SimpleDateFormat("d").format(now);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		return "pages/auth";
	}
	
	private List<Weather> getweatherlive(String live) {
		return service.selectcityName(live);
	}
}