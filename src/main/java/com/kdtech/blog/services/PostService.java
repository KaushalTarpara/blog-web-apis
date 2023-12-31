package com.kdtech.blog.services;

import java.util.List;

import com.kdtech.blog.entities.Post;
import com.kdtech.blog.payloads.PostDto;
import com.kdtech.blog.payloads.PostResponse;

public interface PostService {

	//Create Post
	PostDto createPost(PostDto post, Integer userId, Integer categoryId);
	
	//Update Post
	PostDto updatePost(PostDto post, Integer postId);
	
	//delete Post
	void deletePost(Integer postId);
	
	//get All Post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sotBy, String sortDir);
	
	//Get Post by Id
	PostDto getPostById(Integer postId);
	
	//Get Post by Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//Get Post By User
	List<PostDto> getPostByUser(Integer userId);
	
	// Search Post
	List<PostDto> searchPost(String Keyword);
}
