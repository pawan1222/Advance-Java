package com.example.ProductApp.Service;

import com.example.ProductApp.Model.Product;
import com.example.ProductApp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public  void saveProduct(Product product){
        repository.save(product);
    }

    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return repository.findById(id);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
