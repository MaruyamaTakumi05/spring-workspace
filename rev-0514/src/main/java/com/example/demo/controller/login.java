package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class login {

	private static final String userid = "student";
	private static final String userpas = "himitu";

	@PostMapping("/login")
	public String show(
			@RequestParam("id") String id,
			@RequestParam("pas") String pas,
			Model model) {

		String nextPage = "";
		if (userid.equals(id) && userpas.equals(pas)) {
			nextPage = "loginResult";
		} else {
			String error = "";
			if (!userid.equals(id)) {
				error = "ユーザーidが一致しませんでした";
			} else {
				error = "パスワードが一致しませんでした";
			}
			model.addAttribute("message", error);
			nextPage = "loginResult";
		}

		return nextPage;
	}

	@GetMapping("/login")
	public String loginResult() {
		return "loginForm";
	}

}
