package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Postlist {
	private List<Post> posts = new ArrayList<Post>();

	public List<Post> getPosts() {
		return this.posts;
	}
}
