package com.lpu.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {}

    public Order(String productName) {
        this.productName = productName;
    }

    // Getters and Setters
    public int getId() { 
    	return id; 
    }
    public String getProductName() { 
    	return productName;
    }
    public void setProductName(String productName) {
    	this.productName = productName; 
    }

    public Customer getCustomer() { 
    	return customer; 
    }
    public void setCustomer(Customer customer) { 
    	this.customer = customer; 
    	}
}
