package com.example.blog.service;

import com.example.blog.dao.UserRepositiory;
import com.example.blog.po.User;
import com.example.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepositiory userRepositiory;

    @Override
    public User checkUser(String username, String password) {
        //System.out.println(MD5Util.code(password));
        User user = userRepositiory.findByUsernameAndPassword(username, password);
        return user;
    }
}
