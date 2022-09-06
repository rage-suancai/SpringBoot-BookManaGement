package com.managementboot.service;

import com.managementboot.entity.Book;
import com.managementboot.entity.BorrowDetails;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();

    void deleteBook(int bid);

    void addBook(String title, String desc, double price);

    void borrowBook(int bid, int id);

    List<Book> getAllBookWithOutBorrow();

    List<Book> getAllBorrowedBookById(int id);

    void returnBook(int bid, int id);

    List<BorrowDetails> getBorrowDetailsList();

}
