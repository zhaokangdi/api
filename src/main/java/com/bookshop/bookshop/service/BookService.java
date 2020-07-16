package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BookDAO;
import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.entity.Book;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.RecommendBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    BusinessDAO businessDAO;

    //显示某一图书
    public Book showBookById(int id){ return bookDAO.findById(id); }

    //显示店铺中的全部书
    public List<Book> showAllBooksStore(int store_id){
        return bookDAO.findByStoreId(store_id);
    }

    //按评分降序显示店铺的全部书
    public  List<Book> showAllBooksStoreDesc(int store_id){ return bookDAO.findByStoreIdOrderByStoreIdDesc(store_id);}

    //显示类别下的全部图书
    public List<Book> showAllBooksCategory(String category){
        return bookDAO.findByCategory(category);
    }

    //添加图书
    public void addBook(Book book){

        String id = book.getPicPath();
        Business business = businessDAO.findByPhone(id);
        book.setId(null);
        book.setStoreId(business.getStoreId());
        book.setPicPath("http://images.amazon.com/images/P/0671042858.01.THUMBZZZ.jpg");
        bookDAO.save(book); }

    //删除图书
    public void deleteBooks(Book book){
        bookDAO.deleteById(book.getId());
    }

    //编辑图书信息
    public Book editBooks(Book book){

        Book bookOld = bookDAO.findById(book.getId());
        bookOld.setName(book.getName());
        bookOld.setAuthor(book.getAuthor());
        bookOld.setPublishingHouse(book.getPublishingHouse());
        bookOld.setPublishingDate(book.getPublishingDate());
        bookOld.setPrice(book.getPrice());
        bookOld.setCategory(book.getCategory());
        bookOld.setIntroduction(book.getIntroduction());

        return bookDAO.save(bookOld);
    }

    //读取爬取的热门图书
    /*
    public List<RecommendBook> hotBooks(){

        FileReader f= null;
        ArrayList<RecommendBook> recommendBooks = new ArrayList<>();

        try {
            f = new FileReader("C:\\Users\\lenovo\\Desktop\\ci_yun\\hot_books.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LineNumberReader l=new LineNumberReader(f);
        l.setLineNumber(1);
        for(int i=0;i<=9;i++){
            try {
                RecommendBook recommendBook = new RecommendBook();
                recommendBook.setName(l.readLine());
                recommendBooks.add(recommendBook);
                System.out.println(l.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            l.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recommendBooks;
    }
     */

    //获取不同种类
    public List<Book> categoryBooks(String category){
        return bookDAO.findByCategoryOrderByScoreDesc(category);
    }
}
