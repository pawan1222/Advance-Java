package com.example.LMS.Controller;

import com.example.LMS.Model.Book;
import com.example.LMS.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book,
                          BindingResult result) {

        if (result.hasErrors()) {
            return "add-book";
        }

        service.addBook(book);
        return "redirect:/books/view";
    }

    @GetMapping("/view")
    public String viewBooks(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "view-books";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.getBookById(id));
        return "book-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "redirect:/books/view";
    }
}
