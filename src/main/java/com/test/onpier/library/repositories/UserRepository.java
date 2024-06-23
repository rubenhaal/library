package com.test.onpier.library.repositories;

import com.test.onpier.library.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    @Query("SELECT l FROM LibraryUser l, Borrowed b WHERE l.completeName = b.borrower")
    List<LibraryUser> findUsersThatHaveBorrowedBooks();
}
