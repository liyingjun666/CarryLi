package com.itheima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
//    @Qualifier("xxxx")
    private IBookService bookService ;

    @GetMapping(value = "/test")
    public List<Book> getAll(){
        return bookService.list();
    }

    @PostMapping(value = "/test1")
    public Boolean save(@RequestBody Book book){
        return bookService.save(book);
    }

    @PostMapping(value = "/test2")
    public Boolean update(@RequestBody Book book){
        return bookService.update(book,null);
    }


    @GetMapping(value = "/delete")
    public Boolean delete(@RequestParam Integer id){
        return bookService.removeById(id);
    }

    @GetMapping(value ="test3")
    public Book getById(@RequestParam Integer id){
        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage <Book> getPage(@PathVariable int currentPage, int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}
