package com.managementboot.service.impl;

import com.managementboot.entity.AuthUser;
import com.managementboot.mapper.UserMapper;
import com.managementboot.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public void register(String name, String sex, String grade, String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        AuthUser user = new AuthUser(0, name, encoder.encode(password), "user");
        if (userMapper.addRegisterUser(user) <= 0) throw new RuntimeException(" 用户基本信息添加失败! ");
        if (userMapper.addStudnetInfo(user.getId(), name, grade, sex) <= 0) throw new RuntimeException(" 学生详细信息插入失败! ");

    }

    @Override
    public AuthUser findUser(HttpSession session) {

        AuthUser user = (AuthUser) session.getAttribute("user");

        if (user == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userMapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user", user);
        }

        return user;

    }

}
