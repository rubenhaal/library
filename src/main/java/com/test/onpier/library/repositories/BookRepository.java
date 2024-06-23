package com.test.onpier.library.repositories;

import com.test.onpier.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    @Query("SELECT b FROM Book b, Borrowed o WHERE b.title != o.book")
    List<Book> findAllAvailableBooks();
}
