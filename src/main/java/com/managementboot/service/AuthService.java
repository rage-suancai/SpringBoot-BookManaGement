package com.managementboot.service;

import com.managementboot.entity.AuthUser;

import javax.servlet.http.HttpSession;

public interface AuthService {

    void register(String name, String sex, String grade, String password);

    AuthUser findUser(HttpSession session);

}
