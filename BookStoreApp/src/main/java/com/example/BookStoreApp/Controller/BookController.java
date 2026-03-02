package com.example.BookStoreApp.Controller;

import com.example.BookStoreApp.Entity.Book;
import com.example.BookStoreApp.Service.BookService;
import com.example.BookStoreApp.dto.BookDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto dto){

    }

}
