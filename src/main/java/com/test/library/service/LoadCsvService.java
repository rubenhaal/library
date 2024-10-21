package com.test.library.service;

import com.test.library.repositories.UserRepository;
import com.test.library.entity.Book;
import com.test.library.entity.Borrowed;
import com.test.library.entity.LibraryUser;
import com.test.library.repositories.BookRepository;
import com.test.library.repositories.BorrowedRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.test.library.util.Utils.parseDateFromString;
@Service
@Log
public class LoadCsvService {

    public static final String USER_CSV = "user.csv";
    public static final String BORROWED_CSV = "borrowed.csv";
    public static final String BOOKS_CSV = "books.csv";


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() throws IOException {
        loadUser();
        loadBorrowed();
        loadBook();
    }

    public  void loadUser() throws IOException {

        List<LibraryUser> libraryUsers = new ArrayList<>();

        ClassLoader classLoader = LoadCsvService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(USER_CSV);
        if(inputStream!=null){
            try (
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader())
            ) {
                for (CSVRecord csvRecord : csvParser) {

                    addUser(libraryUsers,csvRecord );
                }
                userRepository.saveAll(libraryUsers);
            }
        }
    }

    public void loadBorrowed() throws IOException {
        List<Borrowed> borrowedList = new ArrayList<>();

        ClassLoader classLoader = LoadCsvService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BORROWED_CSV);
        if(inputStream!=null){
            try (
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader())
            ) {
                for (CSVRecord csvRecord : csvParser) {

                    addBorrowing(borrowedList, csvRecord);
                }
                borrowedRepository.saveAll(borrowedList);
            }
        }
    }

    public void loadBook() throws IOException {

        List<Book> borrowedList = new ArrayList<>();

        ClassLoader classLoader = LoadCsvService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BOOKS_CSV);
        if(inputStream!=null){
            try (
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader())
            ) {

                for (CSVRecord csvRecord : csvParser) {

                    addBook(borrowedList, csvRecord);
                }
                bookRepository.saveAll(borrowedList);
            }
        }
    }

    private void addUser(List<LibraryUser> libraryUsers, CSVRecord record){

        libraryUsers.add(LibraryUser.builder()
                .name(record.get("Name"))
                .firstName(record.get("First name"))
                .completeName(record.get("Name")+","+record.get("First name"))
                .memberSince(parseDateFromString(record.get("Member since")))
                .memberTill(parseDateFromString(record.get("Member till")))
                .gender(record.get("Gender"))
                .build());
    }
    private void addBorrowing(List<Borrowed> borrowedList, CSVRecord record){


        borrowedList.add(Borrowed.builder()
                .borrower(record.get("Borrower"))
                .book(record.get("Book"))
                .borrowedFrom(parseDateFromString(record.get("borrowed from")))
                .borrowedTo(parseDateFromString(record.get("borrowed to")))
                .build());

    }

    private void addBook(List<Book> books, CSVRecord record){

        books.add(Book.builder()
                .title(record.get("Title"))
                .author(record.get("Author"))
                .genre(record.get("Genre"))
                .publisher(record.get("Publisher"))
                .build());
    }
}
