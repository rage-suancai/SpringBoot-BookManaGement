package com.managementboot.controller.api;

import com.managementboot.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/api/admin")
public class AdminApiController {
    @Resource
    BookService bookService;

    @PostMapping(value = "/del-book")
    public String deleteBook(@RequestParam("id") int id) {

        bookService.deleteBook(id);

        return "redirect:/page/admin/book";

    }

    @PostMapping(value = "/add-book")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("desc") String desc,
                          @RequestParam("price") double price) {

        bookService.addBook(title, desc, price);

        return "redirect:/page/admin/add-book";

    }

}
