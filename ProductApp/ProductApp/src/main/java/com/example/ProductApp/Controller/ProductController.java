package com.example.ProductApp.Controller;

import com.example.ProductApp.Model.Product;
import com.example.ProductApp.Repository.ProductRepository;
import com.example.ProductApp.Service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.naming.Binding;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@Controller
@RequestMapping("/products")
@SessionAttributes("product")
public class ProductController {

    @Autowired
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @ModelAttribute("product")
    public Product getProduct(){
        return new Product();
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String viewProduct(Model model){
        model.addAttribute("products", service.getAllProduct());
        return "product-list";
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                               BindingResult result,
                               SessionStatus status){
        if(result.hasErrors()){
            return "product-form";
        }
        service.saveProduct(product);
        status.setComplete();  //clear session attributes
        return "redirect:/products/List";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Product product  = service.getProductById(id)
                .orElseThrow(()->new RuntimeException(("Product Not Found")));
        model.addAttribute("product",product);
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        if(service.getProductById(id).isEmpty()){
            throw new RuntimeException("Cannot delete. Product not found");
        }
        service.deleteProduct(id);
        return "redirect:/product/list";
    }

    @GetMapping("/count")
    public String getProductCount(){
        return "Total Product: "+ service.getAllProduct().size();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handlRuntimeException(RuntimeException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "error-page";
    }
}
