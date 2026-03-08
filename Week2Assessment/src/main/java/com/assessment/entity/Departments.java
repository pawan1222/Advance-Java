package com.assessment.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="DepName")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String DepName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Students> students = new ArrayList<>();

    public Departments() {
    	
    }

    public Departments(String depName) {
        this.DepName = depName;
    }

    public void addStudent(Students student) {
        students.add(student);
        student.setDepartment(this);
    }

    public int getId() { return id; }

    public String getDepName() { return DepName; }
}