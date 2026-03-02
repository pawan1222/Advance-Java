package com.example.ProductApp.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public  String handGlobalException(Exception e, Model model){
        model.addAttribute("errorMessage", "Something went Wrong");
        return "error-page";
    }
}
