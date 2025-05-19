package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "maxPrice", defaultValue = "") Integer maxPrice,
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		
		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		

		// 商品一覧情報の取得
		List<Item> itemList = null;
		
		
		if (categoryId != null) {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		} else if ("priceAsc".equals(sort)) {
			// 安い順
			itemList = itemRepository.findAllByOrderByPriceAsc();
		} else if (maxPrice != null) {
			// ○○円以下
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
		} else if (keyword.length() > 0) {
			// 商品名による部分一致検索 
			//itemList = itemRepository.findByNameLike("%" + keyword + "%");  //  // Likeを利用した場合は「%」が必要です
			itemList = itemRepository.findByNameContaining(keyword);
		} else {
			// 全商品検索
			itemList = itemRepository.findAll();
		}

		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("keyword", keyword);

		model.addAttribute("items", itemList);
		return "items";
	}

//	//	// 商品一覧表示（追加課題）
//		@GetMapping("/items")
//		public String index(
//				@RequestParam(name = "keyword", defaultValue = "") String keyword,
//				@RequestParam(name = "maxPrice", defaultValue = "") Integer maxPrice,
//				@RequestParam(name = "sort", defaultValue = "") String sort,
//				@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
//				Model model) {
//	
//			// 商品一覧情報の取得
//			List<Item> itemList = null;
//			if (keyword.length() > 0) {
//				// キーワードあり
//				if (maxPrice != null) {
//					// 最大価格あり
//					if (sort.equals("priceAsc")) {
//						itemList = itemRepository.findByNameContainingAndPriceLessThanEqualOrderByPriceAsc(keyword,
//								maxPrice);
//					} else {
//						itemList = itemRepository.findByNameContainingAndPriceLessThanEqual(keyword, maxPrice);
//					}
//				} else {
//					// 最大価格なし
//					if (sort.equals("priceAsc")) {
//						itemList = itemRepository.findByNameContainingOrderByPriceAsc(keyword);
//					} else {
//						itemList = itemRepository.findByNameContaining(keyword);
//					}
//				}
//	
//			} else {
//				// キーワードなし
//				if (maxPrice != null) {
//					// 最大価格あり
//					if (sort.equals("priceAsc")) {
//						itemList = itemRepository.findByPriceLessThanEqualOrderByPriceAsc(maxPrice);
//					} else {
//						itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
//					}
//				} else {
//					// 最大価格なし
//					if (sort.equals("priceAsc")) {
//						itemList = itemRepository.findAllByOrderByPriceAsc();
//					} else {
//						itemList = itemRepository.findAll();
//					}
//				}
//			}
//	
//			model.addAttribute("maxPrice", maxPrice);
//			model.addAttribute("keyword", keyword);
//	
//			model.addAttribute("items", itemList);
//			return "items";
//		}
}
