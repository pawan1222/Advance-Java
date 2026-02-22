package com.lpu.MainApp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lpu.Entity.Course;
import com.lpu.Entity.Department;
import com.lpu.Entity.IDCard;
import com.lpu.Entity.Student;

public class MainApp {

    private static SessionFactory factory;

    public static void main(String[] args) {

        factory = new Configuration().configure().buildSessionFactory();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n====== MENU ======");
            System.out.println("1. Create Department");
            System.out.println("2. Create Student");
            System.out.println("3. Create Course");
            System.out.println("4. View Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    createDepartment(sc);
                    break;

                case 2:
                    createStudent(sc);
                    break;

                case 3:
                    createCourse(sc);
                    break;

                case 4:
                    viewStudents();
                    break;

                case 5:
                    deleteStudent(sc);
                    break;

                case 6:
                    factory.close();
                    System.out.println("Application Closed");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


    private static void createDepartment(Scanner sc) {

        System.out.print("Enter Department Name: ");
        String name = sc.nextLine();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Department dept = new Department();
        dept.setName(name);

        session.persist(dept);

        tx.commit();
        session.close();

        System.out.println("Department Created!");
    }

   
    private static void createStudent(Scanner sc) {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department ID: ");
        int deptId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Card Number: ");
        String cardNo = sc.nextLine();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Department dept = session.get(Department.class, deptId);

        if (dept != null) {

            Student student = new Student();
            student.setName(name);

            IDCard id = new IDCard();
            id.setCardNumber(cardNo);

            student.setIdCard(id);
            student.setDepartment(dept);

            dept.getStudents().add(student);

            session.persist(student);

            tx.commit();
            System.out.println("Student Created!");

        } else {
            System.out.println("Department Not Found!");
            tx.rollback();
        }

        session.close();
    }

 
    private static void createCourse(Scanner sc) {

        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Course course = new Course();
        course.setCourseName(courseName);

        session.persist(course);

        tx.commit();
        session.close();

        System.out.println("Course Created!");
    }

   
    private static void viewStudents() {

        Session session = factory.openSession();

        List<Student> list = session.createQuery("from Student", Student.class).list();

        for (Student s : list) {
            System.out.println("ID: " + s.getId() +
                    " Name: " + s.getName() +
                    " Dept: " + s.getDepartment().getName());
        }

        session.close();
    }

   
    private static void deleteStudent(Scanner sc) {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.get(Student.class, id);

        if (student != null) {
            session.remove(student);
            System.out.println("Student Deleted!");
        } else {
            System.out.println("Student Not Found!");
        }

        tx.commit();
        session.close();
    }
}