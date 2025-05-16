package com.example.demo.quiz;

import java.util.Scanner;

public class Quiz3 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.println("お金を入力してください");
		int money = scan.nextInt();

		if (money < 120) {
			System.out.println("お金が足りません");
		}

		if (money > 120) {
			System.out.println("購入出来ました。おつりは" + (money - 120) + "円です");
		}

	}

}
