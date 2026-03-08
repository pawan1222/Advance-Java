package com.assessment.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="Student")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

   
    // OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id")
    private StudentIDCard idCard;
    
    // ManyToOne
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;

    // ManyToMany (Owner Side)
    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Courses> courses = new HashSet<>();

    public Students() {}

    public Students(String name) {
        this.name = name;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public void setIdCard(StudentIDCard idCard) {
        this.idCard = idCard;
    }

//    public void addCourse(Courses course) {
//        courses.add(course);
//        course.getStudents().add(this);
//    }
}