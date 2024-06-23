package com.test.onpier.library.service;

import com.test.onpier.library.entity.Book;
import com.test.onpier.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getAllAvailableBooks(){
        return bookRepository.findAllAvailableBooks();
    }
}
