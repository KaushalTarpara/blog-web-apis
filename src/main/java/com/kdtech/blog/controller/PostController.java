package com.kdtech.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdtech.blog.payloads.ApiResponse;
import com.kdtech.blog.payloads.PostDto;
import com.kdtech.blog.payloads.PostResponse;
import com.kdtech.blog.payloads.UserDto;
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
		public ResponseEntity<PostResponse> getAllPosts(
				@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
				@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
				){
			PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize);
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		}
		
		// Get Post details by id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
			PostDto postDto = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		}
		
		// delete Post
		@DeleteMapping("/post/{postId}")
		public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
			this.postService.deletePost(postId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully!!",true),HttpStatus.OK );
		}
		
		// update Post
		@PutMapping("/post/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId){
			PostDto updatePost = this.postService.updatePost(postDto,postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		}
		
}
