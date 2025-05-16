package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

@Controller
public class CartController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	Cart cart;

	@GetMapping("/cart/clear")
	public String clearCart() {
		List<Item> items = cart.getItems();
		items.clear();
		return "cart";
	}

	@PostMapping("/cart/add")
	public String cart(
			@RequestParam("name") String name,
			@RequestParam(name = "price", defaultValue = "0") int price,
			Model model) {

		List<Item> items = cart.getItems();

		Item item = new Item(name, price);
		items.add(item);
		model.addAttribute("item", item);

		return "cart";
	}

	@PostMapping("/cart/login")
	public String login(@RequestParam("name") String name) {
		//accountのインスタンスに氏名を設定
		account.setName(name);
		return "cart";
	}

	@GetMapping({ "/cart/login", "/cart/logout" })
	public String index() {
		session.invalidate(); //セッションを破棄
		return "cartLogin";
	}
}
