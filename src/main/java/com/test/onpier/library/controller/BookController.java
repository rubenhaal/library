package com.test.onpier.library.controller;

import com.test.onpier.library.entity.Book;
import com.test.onpier.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/getAllBooks")
    public List<Book> getAllBooks(){
        log.debug("getAllBooks endpoint");
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/getAllAvailableBooks")
    public List<Book> getAllAvailableBooks(){
        log.debug("getAllAvailableBooks endpoint");
        return bookService.getAllAvailableBooks();
    }
}
