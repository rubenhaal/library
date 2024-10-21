package com.test.library.repositories;

import com.test.library.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    @Query("SELECT l FROM LibraryUser l, Borrowed b WHERE l.completeName = b.borrower")
    List<LibraryUser> findUsersThatHaveBorrowedBooks();

    @Query("SELECT l FROM LibraryUser l, Borrowed b WHERE l.completeName = b.borrower AND l.memberTill IS NULL AND b.borrowedTo<CURRENT_DATE")
    List<LibraryUser> findActiveUsersWithoutBorrowedBooks();
}
