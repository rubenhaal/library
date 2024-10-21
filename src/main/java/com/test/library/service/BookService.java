package com.test.library.service;

import com.test.library.entity.Book;
import com.test.library.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getAllAvailableBooks(){
        return bookRepository.findAllAvailableBooks();
    }
}
