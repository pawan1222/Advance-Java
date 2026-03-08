package com.example.BookSecurity.Service;

import com.example.BookSecurity.Dto.BookRequest;
import com.example.BookSecurity.Dto.BookResponse;
import com.example.BookSecurity.Entity.Book;
import com.example.BookSecurity.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    //Method security Only ADMIN can ADD books(check before execution)
    @PreAuthorize("hasRole('ADMIN')")
    public BookResponse addBook(BookRequest request){
        Book book = modelMapper.map(request, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookResponse.class);
    }

    @PreAuthorize("hashRole('ADMIN')")
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book->modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }
}
