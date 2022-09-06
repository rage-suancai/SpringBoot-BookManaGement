package com.managementboot.service.impl;

import com.managementboot.entity.Book;
import com.managementboot.entity.Borrow;
import com.managementboot.entity.BorrowDetails;
import com.managementboot.mapper.BookMapper;
import com.managementboot.mapper.UserMapper;
import com.managementboot.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<Book> getAllBook() {

        return bookMapper.allBook();

    }

    @Override
    public void deleteBook(int bid) {

        bookMapper.deleteBook(bid);

    }

    @Override
    public void addBook(String title, String desc, double price) {

        bookMapper.addBook(title, desc, price);

    }

    @Override
    public void borrowBook(int bid, int id) {

        Integer sid = userMapper.getSidByUserId(id);
        if (sid == null) return;
        bookMapper.addBorrow(bid, sid);

    }

    @Override
    public void returnBook(int bid, int id) {

        Integer sid = userMapper.getSidByUserId(id);
        if (sid == null) return;
        bookMapper.deleteBorrow(bid, sid);

    }

    @Override
    public List<BorrowDetails> getBorrowDetailsList() {

        return bookMapper.borrowDetailsList();

    }

    @Override
    public List<Book> getAllBookWithOutBorrow() {

        List<Book> books = bookMapper.allBook();
        List<Integer> borrows = bookMapper.BorrowList()
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());

        return books
                .stream()
                .filter(book -> !borrows.contains(book.getBid()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Book> getAllBorrowedBookById(int id) {

        Integer sid = userMapper.getSidByUserId(id);
        if (sid == null) return Collections.emptyList();

        return bookMapper.borrowListBySid(sid)
                .stream()
                .map(borrow -> bookMapper.getBookById(borrow.getBid()))
                .collect(Collectors.toList());

    }

}
