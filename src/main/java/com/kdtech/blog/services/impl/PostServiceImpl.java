package com.kdtech.blog.services.impl;

import java.util.Date; 
import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.PageRanges;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdtech.blog.entities.Category;
import com.kdtech.blog.entities.Post;
import com.kdtech.blog.entities.User;
import com.kdtech.blog.exceptions.ResourceNotFoundException;
import com.kdtech.blog.payloads.PostDto;
import com.kdtech.blog.payloads.PostResponse;
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
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream().
				map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setPosts(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
		return this.modelMapper.map(post, PostDto.class);
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
	public List<PostDto> searchPost(String Keyword){
		return null;
	}

}
