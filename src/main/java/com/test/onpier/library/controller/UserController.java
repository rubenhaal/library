package com.test.onpier.library.controller;

import com.test.onpier.library.entity.LibraryUser;
import com.test.onpier.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUsers")
    public List<LibraryUser> getAllUsers(){
        log.debug("getAllUsers endpoint");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getUsersWithAtLeastOneBookBorrowed")
    public List<LibraryUser> getUsersWithAtLeastOneBookBorrowed(){
        log.debug("getUsersWithAtLeastOneBookBorrowed endpoint");
        return userService.getUsersWithAtLeastOneBookBorrowed();
    }

    @GetMapping(value = "/notTerminatedUserWithoutBorrowedBooks")
    public List<LibraryUser> getNonTerminatedUserWithoutBorrowedBooks() {
        log.debug("notTerminatedUserWithoutBorrowedBooks endpoint");
        return userService.getAllUsers();
    }


}
