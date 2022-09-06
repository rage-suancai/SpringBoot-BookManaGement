package com.managementboot.mapper;

import com.managementboot.entity.Book;
import com.managementboot.entity.Borrow;
import com.managementboot.entity.BorrowDetails;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> allBook();

    @Delete("delect from book where bid = #{bid}")
    void deleteBook(int bid);

    @Delete("delete from borrow where bid = #{bid} and sid = #{sid}")
    void deleteBorrow(int bid, int sid);

    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);

    @Insert("insert into borrow(bid, sid, `time`) values(#{bid}, #{sid}, NOW())")
    void addBorrow(@Param("bid")int bid, @Param("sid")int sid);

    @Select("select * from borrow")
    List<Borrow> BorrowList();
    @Select("select * from borrow where sid = #{sid}")
    List<Borrow> borrowListBySid(int sid);
    @Select("select * from book where bid = #{bid}")
    Book getBookById(int bid);

    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "book_title"),
            @Result(column = "name", property = "user_name"),
            @Result(column = "time", property = "time"),
    })
    @Select("select * from borrow left join book on book.bid = borrow.bid " +
            "                     left join student on borrow.sid = student.sid")
    List<BorrowDetails> borrowDetailsList();

    @Select("select count(*) from book")
    int getBookCount();

    @Select("select count(*) from borrow")
    int getBorrowCount();

}
