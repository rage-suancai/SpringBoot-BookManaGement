package com.managementboot.controller.api;

import com.managementboot.entity.AuthUser;
import com.managementboot.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/api/user")
public class UserApiController {
    @Resource
    BookService bookService;

    @GetMapping(value = "/borrow-book")
    public String borrowBook(@RequestParam("id") int bid, @SessionAttribute("user") AuthUser user) {

        bookService.borrowBook(bid, user.getId());

        return "redirect:/page/user/book";

    }

    @GetMapping(value = "/return-book")
    public String returnBook(@RequestParam("id") int bid, @SessionAttribute("user") AuthUser user) {

        bookService.returnBook(bid, user.getId());

        return "redirect:/page/user/book";

    }

}
