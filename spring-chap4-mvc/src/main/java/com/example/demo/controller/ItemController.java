package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;

@Controller
public class ItemController {

	@PostMapping("/item")
	public String add(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "0") int price, //integerだと空文字でもOK
			Model model) {

		Item item = new Item(name, price);

		//共用のデータ置き場にリクエストパラメータを置き直す
		model.addAttribute("item", item);

		//画面遷移
		return "item";
	}

	@GetMapping("/item")
	public String index() {
		return "item";
	}

}
