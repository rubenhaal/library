package com.test.onpier.library.repositories;

import com.test.onpier.library.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long> {
}
