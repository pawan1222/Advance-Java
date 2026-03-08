package com.assessment.entity;

import jakarta.persistence.*;

@Entity
@Table(name="IDcard")
public class StudentIDCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cardNumber;

    @OneToOne(mappedBy = "idCard")
    private Students student;

    public StudentIDCard() {}

    public StudentIDCard(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getId() { return id; }

    public int getCardNumber() { return cardNumber; }
}