package com.telusko.joblisting.Controller;

import com.telusko.joblisting.Model.*;
import com.telusko.joblisting.Repository.PostRepository;
import com.telusko.joblisting.Repository.SearchRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	
	@Autowired
	PostRepository repo;
	
	@Autowired
	SearchRepository search;
	
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		return repo.findAll();
	}
	
	@GetMapping("/posts/{text}")
	public List<Post> getPostByText(@PathVariable("text") String text) {
		return search.searchByText(text);
	}
	
	@PostMapping("/newPost")
	public Post addPost(@RequestBody Post post) {
		long count = repo.count();
		post.setId(String.valueOf(count + 1));
		return repo.save(post);
	}
}
