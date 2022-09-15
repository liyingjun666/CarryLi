package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService bookService ;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(3));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试属性");
        book.setName("再见容易再见难");
        book.setDescription("终是你我无缘");
        bookService.save(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(7);
        book.setType("测试属性1111");
        book.setName("再见容易再见难");
        book.setDescription("终是你我无缘");
        bookService.updateById(book);

    }

    @Test
    void testDelete(){
        bookService.removeById(128);
    }
    @Test
    void testGetAll(){
        List<Book> books = bookService.list();
        System.out.println(books);
    }
    @Test
    void testGetPage(){
        IPage<Book> page = new Page<Book>(2,5);
         bookService.page(page);

        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());

    }

}
