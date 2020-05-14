package com.example.blog.service;

import com.example.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    //分页查询
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    Type updateType(Long id, Type type);

    void deleType(Long id);
}
