package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BookDAO;
import com.bookshop.bookshop.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;

    //显示店铺中的全部书
    public List<Book> showAllBooksStore(String store_id){
        return bookDAO.findByStoreId(store_id);
    }

    //按评分降序显示店铺的全部书
    public  List<Book> showAllBooksStoreDesc(String store_id){ return bookDAO.findByStoreIdOrderByStoreIdDesc(store_id);}

    //显示类别下的全部图书
    public List<Book> showAllBooksCategory(String category){
        return bookDAO.findByCategory(category);
    }

    //添加图书
    public void addBooks(Book book){  bookDAO.save(book); }

    //删除图书
    public void deleteBooks(Book book){
        bookDAO.delete(book);
    }

    //编辑图书信息
    public Book editBooks(Book book){

        Book bookOld = bookDAO.findById(book.getId());
        bookOld.setName(book.getName());
        bookOld.setAuthor(book.getAuthor());
        bookOld.setPublishingHouse(book.getPublishingHouse());
        bookOld.setPrice(book.getPrice());
        bookOld.setCategory(book.getCategory());
        bookOld.setIntroduction(book.getIntroduction());

        return bookDAO.save(bookOld);
    }

}
