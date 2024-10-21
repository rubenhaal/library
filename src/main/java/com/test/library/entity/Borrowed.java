package com.test.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //improvement make manyToOne Association
    //private User borrower;
    private String borrower;
    //improvement make manyToOne Association
    //private Book book;
    private String book;
    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;
}
