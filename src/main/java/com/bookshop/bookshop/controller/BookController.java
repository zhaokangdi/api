package com.bookshop.bookshop.controller;

import com.bookshop.bookshop.entity.*;
import com.bookshop.bookshop.result.Result;
import com.bookshop.bookshop.result.ResultFactory;
import com.bookshop.bookshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Autowired
    StoreService storeService;

    @Autowired
    RecommendBookService recommendBookService;

    @CrossOrigin
    @PostMapping("/api/store/allbooks")
    @ResponseBody
    //找到店铺中的所有图书
    public Result findAllStoreBooks(@RequestBody Business business){

       List<Book> books = storeService.checkAllBooksIfHave(business.getPhone());
       String indentification = storeService.checkIdentification(business.getPhone());

       return ResultFactory.buildSuccessResult(books,indentification);
    }

    @CrossOrigin
    @PostMapping("/api/book/delete")
    @ResponseBody
    public Result deleteBook(@RequestBody Book book){

        Book bookOld = bookService.showBookById(book.getId());
        int storeId = bookOld.getStoreId();
        bookService.deleteBooks(book);

        return ResultFactory.buildSuccessResult(bookService.showAllBooksStore(storeId),"下架成功！");
    }

    @CrossOrigin
    @PostMapping("/api/book/info")
    @ResponseBody
    public Result showBookInfo(@RequestBody Book book){

        int id = book.getId();
        return ResultFactory.buildSuccessResult(bookService.showBookById(id),null);
    }

    @CrossOrigin
    @PostMapping("/api/book/update")
    @ResponseBody
    public Result updateBook(@RequestBody Book book){

        Book bookOld = bookService.showBookById(book.getId());
        int storeId = bookOld.getStoreId();
        bookService.editBooks(book);
        return ResultFactory.buildSuccessResult(bookService.showAllBooksStore(storeId),"修改成功！");
    }

    @CrossOrigin
    @PostMapping("/api/book/add")
    @ResponseBody
    public Result addBook(@RequestBody Book book){

        bookService.addBook(book);
        return ResultFactory.buildSuccessResult(book,"上架成功！");
    }


    @CrossOrigin
    @PostMapping("/api/book/renwensheke")
    @ResponseBody
    public Result mainBooks(@RequestBody User user){

        BookMain main = new BookMain();
        List<Book> renwensheke = bookService.categoryBooks("人文社科");

        if(renwensheke.size()>8){
            List<Book> renwenshekeNew = renwensheke.subList(0,7);
            main.setBookList(renwenshekeNew);
        }
        else{
            main.setBookList((ArrayList<Book>) renwensheke);
        }

        ArrayList<RecommendBook> recommendBooks = recommendBookService.showAllRecommend(user.getId());
        main.setRecommendBooks(recommendBooks);

        return ResultFactory.buildSuccessResult(main,null);
    }

    @CrossOrigin
    @PostMapping("/api/book/shehuilishi")
    @ResponseBody
    public Result shehuilishi(){

        List<Book> shehuilishi = bookService.categoryBooks("社会历史");
        if(shehuilishi.size()>8){
            List<Book> shehuilishiN = shehuilishi.subList(0,7);
            return ResultFactory.buildSuccessResult(shehuilishiN,null);
        }
        else{
            return ResultFactory.buildSuccessResult(shehuilishi,null);
        }
    }

    @CrossOrigin
    @PostMapping("/api/book/junshikexue")
    @ResponseBody
    public Result junshikexue(){

        List<Book> junshikexue = bookService.categoryBooks("军事科学");
        if(junshikexue.size()>8){
            List<Book> junshikexueN = junshikexue.subList(0,7);
            return ResultFactory.buildSuccessResult(junshikexueN,null);
        }
        else{
            return ResultFactory.buildSuccessResult(junshikexue,null);
        }
    }

    @CrossOrigin
    @PostMapping("/api/book/weishengbaojian")
    @ResponseBody
    public Result weishengbaojian(){

        List<Book> weishengbaojian = bookService.categoryBooks("卫生保健");
        if(weishengbaojian.size()>8){
            List<Book> weishengbaojianN = weishengbaojian.subList(0,7);
            return ResultFactory.buildSuccessResult(weishengbaojianN,null);
        }
        else{
            return ResultFactory.buildSuccessResult(weishengbaojian,null);
        }
    }

    @CrossOrigin
    @PostMapping("/api/book/yishuzhenshang")
    @ResponseBody
    public Result yishuzhensang(){

        List<Book> yishuzhencang = bookService.categoryBooks("艺术珍赏");
        if(yishuzhencang.size()>8){
            List<Book> yishuzhencangN = yishuzhencang.subList(0,7);
            return ResultFactory.buildSuccessResult(yishuzhencangN,null);
        }
        else{
            return ResultFactory.buildSuccessResult(yishuzhencang,null);
        }
    }


}
