package com.example.blog.service;

import antlr.StringUtils;
import com.example.blog.NotFoundException;
import com.example.blog.dao.TagRepositiory;
import com.example.blog.dao.TypeRepositiory;
import com.example.blog.po.Tag;
import com.example.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepositiory tagRepositiory;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepositiory.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepositiory.findById(id).get();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepositiory.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepositiory.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepositiory.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepositiory.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {//1,2,3的方式
        return tagRepositiory.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for(int i = 0; i < idarray.length; i ++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepositiory.findById(id).get();
        if(t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepositiory.save(t);
    }

    @Transactional
    @Override
    public void deleTag(Long id) {
        tagRepositiory.deleteById(id);
    }
}
