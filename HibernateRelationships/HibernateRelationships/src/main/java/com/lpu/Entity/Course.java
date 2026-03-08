package com.lpu.Entity;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String course_name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String course_name) {
        this.course_name = course_name;
    }

    public int getId() { return id; }

    public String getCourse_name() { return course_name; }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
