package com.example.Online_Course_Management_Work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineCourseManagementWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineCourseManagementWorkApplication.class, args);
    }
}

