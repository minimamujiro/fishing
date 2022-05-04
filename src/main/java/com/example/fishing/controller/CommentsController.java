package com.example.fishing.controller;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fishing.entity.Comment;
import com.example.fishing.form.CommentForm;
import com.example.fishing.repository.CommentRepository;

@Controller
public class CommentsController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepository repository;
	
	@RequestMapping(value = "/topics/{topicId}/comments/new")
	public String newComment(@PathVariable("topicId") long topicId, Model model) {
		CommentForm form = new CommentForm();
		form.setTopicId(topicId);
		model.addAttribute("form", form);
		
		return "comments/new";
	}
	
	@RequestMapping(value = "/topics/{topicId}/comment")
	public String create(@PathVariable("topicId") long topicId, @Validated @ModelAttribute("form") CommentForm form, BindingResult result, Model model, RedirectAttributes redirAttrs, Locale locale) {
		if (result.hasErrors()) {
			model.addAttribute("hasMessage", true);
			model.addAttribute("class", "alert-danger");
			model.addAttribute("message", messageSource.getMessage("comments.create.flash.1", new String[] {}, locale));
			return "comments/new";
			
		}
		
		Comment entity = modelMapper.map(form, Comment.class);
		entity.setTopicId(topicId);
		repository.saveAndFlush(entity);
		
		redirAttrs.addFlashAttribute("hasMessage", true);
		redirAttrs.addFlashAttribute("class", "alert-info");
		redirAttrs.addFlashAttribute("message", messageSource.getMessage("comments.create.flash.2", new String[] {}, locale));
		
		return "redirect:/topics";
		
}


}
