package com.managementboot.controller.page;

import com.managementboot.service.AuthService;
import com.managementboot.service.BookService;
import com.managementboot.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/page/admin")
public class AdminPageController {
    @Resource
    AuthService authService;
    @Resource
    BookService bookService;
    @Resource
    StatService statService;

    @GetMapping(value = "/index")
    public String index(HttpSession session, Model model) {

        model.addAttribute("user", authService.findUser(session));
        model.addAttribute("borrowList", bookService.getBorrowDetailsList());
        model.addAttribute("stat", statService.getGlobalStat());

        return "/admin/index";

    }

    @GetMapping(value = "/book")
    public String book(HttpSession session, Model model) {

        model.addAttribute("user", authService.findUser(session));
        model.addAttribute("bookList", bookService.getAllBook());

        return "/admin/book";

    }

    @GetMapping(value = "/add-book")
    public String addBook(HttpSession session, Model model) {

        model.addAttribute("user", authService.findUser(session));

        return "/admin/add-book";

    }

}
