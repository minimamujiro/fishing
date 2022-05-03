package com.example.fishing.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fishing.entity.User;
import com.example.fishing.entity.User.Authority;
import com.example.fishing.form.UserForm;
import com.example.fishing.repository.UserRepository;

@Controller
public class UsersController {
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	/*user#newのゲットとマッピングする
	 * モデルに"form"と名づけてUserFormの値を保存する
	 * */
	@GetMapping(path = "/users/new")
	public String newUser(Model model) {
		model.addAttribute("form", new UserForm());
		return "users/new";
	}
	/*userのリクエストとマッピングする
	 * 引数で受け取ったものから値を変数に代入する
	 * */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	/*HTMLからformと名づけられた値を@ModelAttributeでの引数とする*/
	public String create(@Validated @ModelAttribute("form") UserForm form, BindingResult result, Model model, RedirectAttributes redirAttrs, Locale locale) {
		String name = form.getName();
		String email = form.getEmail();
		String password =form.getPassword();
		String passwordConfirmation = form.getPasswordConfirmation();
		String live = form.getLive();
		/*UserRepositoryにemailの値があるか確認
		 *findByUsersしているのでからじゃない＝使われているメールアドレス
		 * */
		if (repository.findByUsername(email) != null) {
			FieldError fieldError = new FieldError(result.getObjectName(), "email", messageSource.getMessage("users.create.error.1", new String[] {}, locale));
			result.addError(fieldError);
		}
		if (result.hasErrors()) {
			model.addAttribute("hasMessage", true);
			model.addAttribute("class","alert-danger");
			model.addAttribute("message", messageSource.getMessage("users.create.flash.1", new String[] {}, locale));
			return "users/new";
		}
		/*ユーザー登録する
		 * ローカル変数を引数にユーザーをインスタント化してenntityに代入
		 * savaAndFlushでUserRepositoryに値を代入
		 * */
		User entity = new User(email, name, passwordEncoder.encode(password), Authority.ROLE_USER, live);
		repository.saveAndFlush(entity);

		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-info");
		model.addAttribute("message", messageSource.getMessage("users.create.flash.2", new String[] {}, locale));

		return "sessions/new";
		}
}
