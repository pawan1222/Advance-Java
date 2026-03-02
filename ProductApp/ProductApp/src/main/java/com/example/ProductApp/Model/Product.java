package com.example.ProductApp.Model;

import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Product {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "price is requird")
    @Positive(message = "price must be positive")
    private double price;

    @NotNull(message = "Quentity is required")
    @Min(value = 1,message = "Quantity must be atleat 1")
    @Max(value = 20,message = "Quantity must be less than 20")
    private int quantity;


    public Product() {

    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
