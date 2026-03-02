package com.example.ProductApp.Controller;

import com.example.ProductApp.Model.Product;
import com.example.ProductApp.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {

    @Autowired
    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return service.getProductById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    @PostMapping
    public String addProduct(@RequestBody Product product){
        service.saveProduct(product);
        return "Product added successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return "Product deleted successfully";
    }
}
