package com.test.onpier.library.service;


import com.test.onpier.library.entity.Borrowed;
import com.test.onpier.library.entity.LibraryUser;
import com.test.onpier.library.repositories.BorrowedRepository;
import com.test.onpier.library.repositories.UserRepository;
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
class UserServiceTest {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup(){
        userRepository.deleteAll();
        userRepository.flush();
        borrowedRepository.deleteAll();
        borrowedRepository.flush();

        LibraryUser user1= LibraryUser.builder()
                .firstName("name1")
                .name("name1")
                .memberSince(LocalDate.now())
                .memberTill(LocalDate.now())
                .gender("male")
                .completeName("name1,name1")
                .build();
        userRepository.save(user1);
        LibraryUser user2= LibraryUser.builder()
                .firstName("name2")
                .name("name2")
                .memberSince(LocalDate.now())
                .memberTill(LocalDate.now())
                .gender("female")
                .completeName("name2,name2")
                .build();

        userRepository.save(user2);

        Borrowed borrowed = Borrowed.builder()
                .borrower("name1,name1")
                .borrowedTo(LocalDate.now())
                .borrowedFrom(LocalDate.now()).build();
        borrowedRepository.save(borrowed);

    }

    @Test
    void shouldReturnAllUsers(){
        List<LibraryUser> usersWithAtLeastOneBookBorrowed = userService.getAllUsers();
        assertEquals(2, usersWithAtLeastOneBookBorrowed.size());
    }

    @Test
    void shouldReturnAllUsersThathaveAtLEastOneBookBorrowed(){

        //When
        List<LibraryUser> usersWithAtLeastOneBookBorrowed = userService.getUsersWithAtLeastOneBookBorrowed();
        assertEquals(1, usersWithAtLeastOneBookBorrowed.size());

    }

}