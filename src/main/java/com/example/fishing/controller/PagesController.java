package com.example.fishing.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fishing.service.S3Wrapper;

@Controller
public class PagesController {

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
}