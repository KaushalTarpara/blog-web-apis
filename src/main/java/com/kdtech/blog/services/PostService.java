package com.kdtech.blog.services;

import java.util.List;

import com.kdtech.blog.entities.Post;
import com.kdtech.blog.payloads.PostDto;

public interface PostService {

	//Create Post
	PostDto createPost(PostDto post, Integer userId, Integer categoryId);
	
	//Update Post
	Post updatePost(PostDto post, Integer postId);
	
	//delete Post
	void deletePost(Integer postId);
	
	//get All Post
	List<Post> getAllPost();
	
	//Get Post by Id
	Post getPostById(Integer postId);
	
	//Get Post by Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//Get Post By User
	List<PostDto> getPostByUser(Integer userId);
	
	// Search Post
	List<Post> searchPost(String Keyword);
}
