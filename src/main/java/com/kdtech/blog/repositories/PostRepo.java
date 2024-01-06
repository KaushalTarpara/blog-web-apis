package com.kdtech.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdtech.blog.entities.Category;
import com.kdtech.blog.entities.Post;
import com.kdtech.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
