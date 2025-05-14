package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.RandomStringService;

@Controller
public class RandomStringController {

	//作成するボタンをクリックした時
	@PostMapping("/random")
	public String generate(
			@RequestParam("charLength") int charLength,
			@RequestParam(name = "withNumber", defaultValue = "fauls") boolean withNumber,
			Model model) {

		//ランダム文字列の生成と共用のデータ置き場に設定
		RandomStringService service = new RandomStringService();
		List list = service.generate(charLength, withNumber);
		//画面遷移

		model.addAttribute("randList", list);

		return "random";
	}

	//初期表示
	@GetMapping("/random")
	public String index() {
		return "random";
	}

}
