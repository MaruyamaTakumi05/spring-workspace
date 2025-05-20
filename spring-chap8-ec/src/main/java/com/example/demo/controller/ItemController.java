package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {

		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		return "itemDetail";
	}

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "maxPrice", defaultValue = "") Integer maxPrice,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		model.addAttribute("keyword", keyword);
		model.addAttribute("maxPrice", maxPrice);

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null) {
			itemList = itemRepository.findAll();
		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}

		if (!keyword.isEmpty()) {
			itemList = itemRepository.findByNameLike("%" + keyword + "%");
		}

		if (maxPrice == null) {
			itemList = itemRepository.findAll();
		} else {
			itemList = itemRepository.findByNameLikeAndPriceLessThanEqual("%" + keyword + "%", maxPrice);
		}

		model.addAttribute("items", itemList);

		return "items";
	}
}
