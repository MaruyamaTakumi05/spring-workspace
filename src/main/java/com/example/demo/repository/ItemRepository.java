package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	// カテゴリーIDによる検索
	// SELECT * FROM items WHERE category_id = ?
	List<Item> findByCategoryId(Integer categoryId);

	// 価格の安い順
	// SELECT * FROM items ORDER BY price
	List<Item> findAllByOrderByPriceAsc();

	// 価格検索（以下）
	// SELECT * FROM items WHERE price <= ?
	List<Item> findByPriceLessThanEqual(Integer maxPrice);

	List<Item> findByPriceLessThanEqualOrderByPriceAsc(Integer maxPrice);

	// SELECT * FROM items WHERE name LIKE ?
	// List<Item> findByNameLike(String keyword);
	List<Item> findByNameContaining(String keyword);

	List<Item> findByNameContainingOrderByPriceAsc(String keyword);

	// SELECT * FROM items WHERE name LIKE ? AND price <= ?
	List<Item> findByNameContainingAndPriceLessThanEqual(String keyword, Integer maxPrice);

	List<Item> findByNameContainingAndPriceLessThanEqualOrderByPriceAsc(String keyword, Integer maxPrice);

}
