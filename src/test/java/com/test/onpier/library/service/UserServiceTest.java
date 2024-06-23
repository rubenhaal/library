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
        //Active Users
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
                .gender("female")
                .completeName("name2,name2")
                .build();

        userRepository.save(user2);

        //Deactivate Users

        LibraryUser user3= LibraryUser.builder()
                .firstName("name3")
                .name("name3")
                .memberSince(LocalDate.now().minusYears(5))
                .gender("male")
                .completeName("name3,name3")
                .build();
        userRepository.save(user3);

        Borrowed borrowed = Borrowed.builder()
                .borrower("name1,name1")
                .borrowedTo(LocalDate.of(2010,12,2))
                .borrowedFrom(LocalDate.of(2010,10,2)).build();
        borrowedRepository.save(borrowed);

        Borrowed borrowed1 = Borrowed.builder()
                .borrower("name1,name1")
                .borrowedTo(LocalDate.now().plusMonths(2))
                .borrowedFrom(LocalDate.now()).build();
        borrowedRepository.save(borrowed1);

        Borrowed borrowed2 = Borrowed.builder()
                .borrower("name3,name3")
                .borrowedTo(LocalDate.of(2010,12,2))
                .borrowedFrom(LocalDate.of(2010,10,2)).build();

        borrowedRepository.save(borrowed2);

    }

    @Test
    void shouldReturnAllUsers(){
        List<LibraryUser> usersWithAtLeastOneBookBorrowed = userService.getAllUsers();
        assertEquals(3, usersWithAtLeastOneBookBorrowed.size());
    }

    @Test
    void shouldReturnAllUsersThatHaveAtLEastOneBookBorrowed(){

        //When
        List<LibraryUser> usersWithAtLeastOneBookBorrowed = userService.getUsersWithAtLeastOneBookBorrowed();
        assertEquals(2, usersWithAtLeastOneBookBorrowed.size());
        assertEquals("name1,name1", usersWithAtLeastOneBookBorrowed.get(0).getCompleteName());

    }

    @Test
    void shouldReturnUsers_whenUserAreActiveAndHaveCurrentlyNotBookBorrowed(){
        List<LibraryUser> usersWithAtLeastOneBookBorrowed = userService.getActiveUsersWithoutBorrowedBooks();
        assertEquals(1, usersWithAtLeastOneBookBorrowed.size());
        assertEquals("name3,name3", usersWithAtLeastOneBookBorrowed.get(0).getCompleteName());
    }

}