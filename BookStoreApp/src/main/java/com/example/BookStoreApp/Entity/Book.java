package com.example.BookStoreApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Getter
//    @Setter
    private String title;
//    @Setter
//    @Getter
    private String author;
//    @Setter
//    @Getter
    private double price;

//    public Book(String title, String author, double price) {
//        this.title = title;
//        this.author = author;
//        this.price = price;
//    }
}
