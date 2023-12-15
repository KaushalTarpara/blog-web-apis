package com.kdtech.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdtech.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
