package com.itheima.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.controller.utils.R;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
//    @Qualifier("xxxx")
    private IBookService bookService ;

    @GetMapping(value = "/test")
    public R getAll(){
        return  new R(true,bookService.list());
//        return bookService.list();
    }

    @PostMapping(value = "/test1")
    public R save(@RequestBody Book book){

//        R r = new R();
//        boolean flag = bookService.save(book);
//        r.setFlag(flag);
//        new R(bookService.save(book));

        return  new R(bookService.save(book));
    }

    @PostMapping(value = "/test2")
    public R update(@RequestParam Book book){

        return  new R(bookService.update(book,null));

//        return bookService.update(book,null);
    }


    @GetMapping(value = "/delete")
    public R delete(@RequestParam Integer id){

//        return bookService.removeById(id);
        return  new R(bookService.removeById(id));

    }

    @GetMapping(value ="test3")
    public R getById(@RequestParam Integer id){
         return new R(true,bookService.getById(id));

//        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage <Book> getPage(@PathVariable int currentPage, int pageSize){

        return (IPage<Book>) new R(true,bookService.getPage(currentPage,pageSize));
//        return bookService.getPage(currentPage,pageSize);
    }
}
