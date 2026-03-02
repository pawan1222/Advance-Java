package com.example.BookStoreApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookDto {
    @NotBlank(message = "Book should have title")
    private String title;

    @NotBlank(message = "Book should have an Author")
    private String author;

    @NotBlank
    @Min(value = 100, message = "min price should be 100")
    private double price;
}
