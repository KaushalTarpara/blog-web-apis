package com.kdtech.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdtech.blog.entities.Category;
import com.kdtech.blog.entities.Post;
import com.kdtech.blog.entities.User;
import com.kdtech.blog.exceptions.ResourceNotFoundException;
import com.kdtech.blog.payloads.PostDto;
import com.kdtech.blog.repositories.CategoryRepo;
import com.kdtech.blog.repositories.PostRepo;
import com.kdtech.blog.repositories.UserRepo;
import com.kdtech.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private  PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
	
		User user= this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User ID", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		Post post = this.modelMapper.map(postDto,Post.class);
		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto post, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		// Fetch Category using Category Id
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		// Fetch posts using category 
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		// convert Post to PostDto
		List<PostDto> postDtos = posts.stream().
				map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		// Fetch User using user Id
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "user ID", userId));

		// Fetch posts using User
		List<Post> posts = this.postRepo.findByUser(user);

		// convert Post to PostDto
		List<PostDto> postDtos = posts.stream().
				map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}
	
	@Override
	public List<Post> searchPost(String Keyword){
		return null;
	}

}