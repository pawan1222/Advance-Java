package com.assessment.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="Courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private Set<Students> students = new HashSet<>();

    public Courses() {}

    public Courses(String courseName) {
        this.courseName = courseName;
    }

    public int getId() { return id; }

    public String getCourseName() { return courseName; }

    public Set<Students> getStudents() {
        return students;
    }

}