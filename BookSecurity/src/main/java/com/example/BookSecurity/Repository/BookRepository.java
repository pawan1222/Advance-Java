package com.example.BookSecurity.Repository;

import com.example.BookSecurity.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Long> {

}
