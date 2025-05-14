package com.example.demo.controller_example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model_example.Account;

@Controller
public class AccountController {

	/**
	 * 登録完了画面表示
	 * @param name     氏名
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @param model    遷移先にデータを引き継ぐスコープ
	 * @return 登録完了画面のThymeleafテンプレート名
	 */
	@PostMapping("/account")
	public String store(@RequestParam String name,
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		// リクエストパラメータから会員クラスのインスタンスを生成
		Account account = new Account(name, email, password);
		// 遷移先画面に引き継ぐために会員インスタンスをスコープに登録
		model.addAttribute("account", account);
		// 画面遷移
		return "example/accountFinish";
	}

	/**
	 * 確認画面表示
	 * @param name     氏名
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @param model    遷移先にデータを引き継ぐスコープ
	 * @return 確認画面のThymeleafテンプレート名
	 */
	@PostMapping("/account/confirm")
	public String confirm(@RequestParam String name, // 氏名
			@RequestParam String email, // メールアドレス
			@RequestParam String password, // パスワード
			Model model // 遷移先画面にデータを引き継ぐ場合
	) {
		// リクエストパラメータの入力値チェック
		List<String> errors = new ArrayList<String>();
		// 氏名の入力値チェック
		if (name == null || name.isEmpty()) {
			// 必須入力エラー
			errors.add("氏名は必須です");
		} else if (name.length() > 20) {
			// 文字数エラー
			errors.add("氏名は20文字以内で入力してください");
		}
		// メールアドレスの入力値チェック
		if (email == null || email.isEmpty()) {
			// 必須入力エラー
			errors.add("メールアドレスは必須です");
		}
		// パスワードの入力値チェック
		if (password == null || password.isEmpty()) {
			// 必須入力エラー
			errors.add("パスワードは必須です");
		}

		// 取得したリクエストパラメータから会員クラスのインスタンスを生成
		Account account = new Account(name, email, password);

		// 入力値チェックの結果によって遷移先を分岐
		if (errors.size() > 0) {
			// エラーリストを遷移先で引き継ぐためにスコープに登録
			model.addAttribute("errors", errors);
			// 入力値を再表示
			model.addAttribute("account", account);
			// 画面遷移
			return "example/accountForm";
		}

		// 会員のインスタンスを自画面に引き継ぐためにスコープに登録
		model.addAttribute("account", account);

		// 画面遷移
		return "example/accountConfirm";
	}

	/**
	 * 初期画面表示
	 * @return 初期画面のThymeleafテンプレート名
	 */
	@GetMapping("/account")
	public String index(Model model) {
		// 空の会員インスタンスを自画面に引き継ぐためにスコープに登録
		model.addAttribute("account", new Account());
		// 初期画面表示
		return "example/accountForm";
	}
}
