package com.test.library.repositories;

import com.test.library.entity.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
}
