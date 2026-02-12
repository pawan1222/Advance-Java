package com.lpu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_Table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ✅ GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // ✅ SETTERS (VERY IMPORTANT FOR UPDATE)
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // ✅ toString (for printing)
    @Override
    public String toString() {
        return id + " " + name + " " + age;
    }
}
