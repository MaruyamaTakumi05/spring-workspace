package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component //springで管理して欲しいよ宣言
@SessionScope // SessionScopeに管理しておくよ宣言
public class Account {
	private String name;

	public Account() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
