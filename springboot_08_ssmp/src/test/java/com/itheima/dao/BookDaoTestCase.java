package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(3));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试属性");
        book.setName("再见容易再见难");
        book.setDescription("终是你我无缘");
        bookDao.insert(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(111);
        book.setType("测试属性1111");
        book.setName("再见容易再见难");
        book.setDescription("终是你我无缘");
        bookDao.updateById(book);

    }

    @Test
    void testDelete(){
        bookDao.deleteById(128);
    }
    @Test
    void testGetAll(){
        List<Book> books = bookDao.selectList(null);
        System.out.println(books);
    }
    @Test
    void testGetPage(){
        IPage page = new Page(2,5);
        IPage iPage = bookDao.selectPage(page, null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());

    }

    @Test
    void testGetBy(){
        QueryWrapper<Book> qw = new QueryWrapper<>();

        qw.like("name","再见");
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy2(){
        String name = "1" ;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();

        lqw.like(name!=null ,Book::getName,"再见");
        bookDao.selectList(lqw);
    }


}
