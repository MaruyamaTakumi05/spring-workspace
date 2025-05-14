package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	@PostMapping("/account/confirm")
	public String show(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		List<String> errorlist = new ArrayList<String>();

		if (name.isEmpty()) {
			errorlist.add("名前は必須です");
		} else if (name.length() > 20) {
			errorlist.add("名前は20文字以内で入力してください");
		}

		if (email.isEmpty()) {
			errorlist.add("メールアドレスは必須です");
		}

		if (password.isEmpty()) {
			errorlist.add("パスワードは必須です");
		}
		Account account = new Account(name, email, password);
		if (errorlist.size() > 0) {
			model.addAttribute("errorlist", errorlist);
			model.addAttribute("account", account);
			return "accountForm";
		}

		//共用のデータ置き場にリクエストパラメータを置き直す

		//		model.addAttribute("account", new Account(name, password));
		model.addAttribute("account", account);
		return "accountConfirm";
	}

	@GetMapping("/account")
	public String show(Model model) {
		model.addAttribute("account", new Account());
		return "accountForm";
	}

}
