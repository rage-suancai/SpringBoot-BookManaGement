package com.managementboot.controller.api;

import com.managementboot.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.naming.Name;

@Controller
@RequestMapping(value = "/api/auth")
public class AuthApiController {
    @Resource
    AuthService authService;

    @PostMapping(value = "/register")
    public String register(@RequestParam("username") String name,
                           @RequestParam("sex") String sex,
                           @RequestParam("grade") String grade,
                           @RequestParam("password") String password) {

        authService.register(name, sex, grade, password);

        return "redirect:/login";

    }


}
