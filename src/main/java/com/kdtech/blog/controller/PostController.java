package com.kdtech.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdtech.blog.payloads.PostDto;
import com.kdtech.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
		@Autowired
		private PostService postService;

		//Create
		@PostMapping("/user/{userId}/category/{categoryId}/posts")
		public ResponseEntity<PostDto> createPost
							(@RequestBody PostDto postDto, 
							 @PathVariable Integer userId, 
							 @PathVariable Integer categoryId){
			
			PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
			return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		}
		
		
		// Get by Category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
			List<PostDto> posts = this.postService.getPostByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
				
		// Get by User
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
			List<PostDto> posts = this.postService.getPostByUser(userId);
			return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
		
		// Get all Post
		@GetMapping("/posts")
		public ResponseEntity<List<PostDto>> getAllPosts(){
			List<PostDto> allPosts = this.postService.getAllPost();
			return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
		}
		
		// Get Post details by id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
			PostDto postDto = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		}
		
}
