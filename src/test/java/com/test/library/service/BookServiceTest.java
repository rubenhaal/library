package com.test.library.service;

import com.test.library.repositories.BookRepository;
import com.test.library.repositories.BorrowedRepository;
import com.test.library.entity.Book;
import com.test.library.entity.Borrowed;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setup(){
        bookRepository.deleteAll();
        bookRepository.flush();
        borrowedRepository.deleteAll();
        borrowedRepository.flush();

        Book book = Book.builder()
                .title("test1")
                .publisher("publish")
                .author("Ralph")
                .build();
        bookRepository.save(book);
        Book book1 = Book.builder()
                .title("test2")
                .publisher("publish")
                .author("Ralph")
                .build();
        bookRepository.save(book1);



        Borrowed borrowed = Borrowed.builder()
                .book("test1")
                .borrower("name1,name1")
                .borrowedTo(LocalDate.now())
                .borrowedFrom(LocalDate.now()).build();
        borrowedRepository.save(borrowed);

    }

    @Test
    void shouldReturnAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        assertEquals(2, allBooks.size());
    }

    @Test
    void shouldReturnAllAvailableBooks(){
        List<Book> allAvailableBooks = bookService.getAllAvailableBooks();
        assertEquals(1, allAvailableBooks.size());
    }

}