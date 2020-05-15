package com.example.blog.service;

import com.example.blog.NotFoundException;
import com.example.blog.dao.TypeRepositiory;
import com.example.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepositiory typeRepositiory;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepositiory.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepositiory.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepositiory.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepositiory.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepositiory.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepositiory.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepositiory.findById(id).get();
        if(t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepositiory.save(t);
    }

    @Transactional
    @Override
    public void deleType(Long id) {
        typeRepositiory.deleteById(id);
    }
}
