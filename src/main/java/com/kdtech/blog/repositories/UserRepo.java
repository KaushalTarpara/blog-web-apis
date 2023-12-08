package com.kdtech.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.kdtech.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
