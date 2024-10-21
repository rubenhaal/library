package com.test.library.service;

import com.test.library.entity.LibraryUser;
import com.test.library.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<LibraryUser> getAllUsers(){
        return userRepository.findAll();
    }

    public List<LibraryUser> getUsersWithAtLeastOneBookBorrowed(){
        return userRepository.findUsersThatHaveBorrowedBooks();
    }

    public List<LibraryUser> getActiveUsersWithoutBorrowedBooks(){
        return userRepository.findActiveUsersWithoutBorrowedBooks();
    }
}
