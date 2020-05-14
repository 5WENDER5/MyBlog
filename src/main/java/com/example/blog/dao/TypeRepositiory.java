package com.example.blog.dao;

import com.example.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepositiory extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
