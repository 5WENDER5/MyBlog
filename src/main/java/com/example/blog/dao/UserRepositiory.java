package com.example.blog.dao;

import com.example.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserRepositiory extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
