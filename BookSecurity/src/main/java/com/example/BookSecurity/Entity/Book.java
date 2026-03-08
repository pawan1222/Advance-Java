package com.example.BookSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

}
