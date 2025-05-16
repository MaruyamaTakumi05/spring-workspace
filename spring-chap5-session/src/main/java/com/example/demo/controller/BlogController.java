package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.model.Postlist;

@Controller
public class BlogController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	Postlist postlist;

	@PostMapping("/blog")
	public String posts(
			@RequestParam("title") String title,
			@RequestParam("content") String content) {

		//セッションに登録されている投稿リストを取得
		List<Post> allPosts = postlist.getPosts();
		//リクエストパラメータをもとにして投稿記事をインスタンス化
		Post post = new Post(title, content);
		//投稿記事のインスタンスを投稿リストに追加
		allPosts.add(post);
		return "blog";
	}

	@PostMapping("/login")
	public String login(@RequestParam("name") String name) {
		//accountのインスタンスに氏名を設定
		account.setName(name);
		return "blog";
	}

	/**
	 * ログイン画面を表示する
	 * @return
	 */
	@GetMapping({ "/", "/logout" })
	public String index() {
		session.invalidate(); //セッションを破棄
		return "login";
	}
}
