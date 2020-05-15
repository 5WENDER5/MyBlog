package com.example.blog.dao;

import com.example.blog.po.Tag;
import com.example.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepositiory extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}
