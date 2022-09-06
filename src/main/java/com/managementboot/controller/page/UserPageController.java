package com.managementboot.controller.page;

import com.managementboot.entity.AuthUser;
import com.managementboot.service.AuthService;
import com.managementboot.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/page/user")
public class UserPageController {
    @Resource
    AuthService authService;
    @Resource
    BookService bookService;

    @GetMapping(value = "/index")
    public String index(HttpSession session, Model model) {

        model.addAttribute("user", authService.findUser(session));
        model.addAttribute("admin", bookService.getAllBookWithOutBorrow());

        return "/user/index";

    }

    @GetMapping(value = "/book")
    public String book(HttpSession session, Model model) {

        AuthUser user = authService.findUser(session);
        model.addAttribute("user", user);
        model.addAttribute("bookList", bookService.getAllBorrowedBookById(user.getId()));

        return "/user/book";

    }

}
