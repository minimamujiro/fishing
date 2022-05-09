package com.example.fishing.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionsController {
	
	@Autowired
	private MessageSource messageSource;
	/*htmlのformタグで/loginと名づけられたものがsubmitされた時に動く
	 * ログインページのsessions/newを返す
	 * */
	@GetMapping(path = "/login")
	public String index() {
		return "sessions/new";
	}
	/*ログイン情報が間違っている時に動く*/
	@GetMapping(path = "/login-failure")
	public String loginFailure(Model model, Locale locale) {
		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-danger");
		model.addAttribute("message", messageSource.getMessage("sessions.loginFailure.flash", new String[] {}, locale));

		return "sessions/new";
	}
	/*ログアウト時に動く*/
	@GetMapping(path = "/logout-complete")
	public String logoutComplete(Model model, Locale local) {
		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-info");
		model.addAttribute("message", messageSource.getMessage("sessions.logoutComplete.flash", new String[] {}, local));

		return "pages/index";
	}
}