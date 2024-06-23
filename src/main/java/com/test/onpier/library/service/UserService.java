package com.test.onpier.library.service;

import com.test.onpier.library.entity.LibraryUser;
import com.test.onpier.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<LibraryUser> getAllUsers(){
        return userRepository.findAll();
    }

    public List<LibraryUser> getUsersWithAtLeastOneBookBorrowed(){
        return userRepository.findUsersThatHaveBorrowedBooks();
    }

}
