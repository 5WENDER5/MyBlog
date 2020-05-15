package com.example.blog.dao;

import com.example.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TypeRepositiory extends JpaRepository<Type, Long> {

    Type findByName(String name);

    //按分页的方式去查询Type对象
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
