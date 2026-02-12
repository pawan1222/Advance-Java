//package com.lpu;
//
//import java.util.Scanner;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//import com.lpu.entity.Student;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter age: ");
//        int age = sc.nextInt();
//
//        createStudent(name, age);
//
//        sc.close();
//    }
//
//    public static void createStudent(String name, int age) {
//
//        SessionFactory factory = null;
//        Session session = null;
//        Transaction tx = null;
//
//        try {
//            Configuration cfg = new Configuration().configure("hibernate.cgf.xml");
//            factory = cfg.buildSessionFactory();
//
//            session = factory.openSession();
//            tx = session.beginTransaction();
//
//            Student s = new Student(name, age);
//
//            session.persist(s);
//
//            tx.commit();
//
//            System.out.println("Data inserted!");
//
//        } catch (Exception e) {
//
//            if (tx != null) tx.rollback();
//            System.out.println("Error: " + e.getMessage());
//
//        } finally {
//
//            if (session != null) session.close();
//            if (factory != null) factory.close();
//        }
//    }
//}



package com.lpu;

import java.util.Scanner;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lpu.entity.Student;

public class App {
	 static SessionFactory factory;   // ⭐ GLOBAL
    
    // ---------- CREATE ----------
    public static void createStudent(String name, int age) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = new Student(name, age);
            session.persist(s);

            tx.commit();
            System.out.println("Student inserted!");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    // ---------- READ ----------
    public static void getStudent(int id) {

        Session session = factory.openSession();

        try {
            Student s = session.get(Student.class, id);

            if (s != null) {
                System.out.println("Found: " + s.getId() + " " + s.getName() + " " + s.getAge());
            } else {
                System.out.println("Student not found");
            }

        } finally {
            session.close();
        }
    }


    // ---------- UPDATE ----------
    public static void updateStudent(int id, String newName, int newAge) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = session.get(Student.class, id);

            if (s != null) {
                s.setName(newName);
                s.setAge(newAge);

                session.update(s);
                tx.commit();
                System.out.println("Student updated!");
            } else {
                System.out.println("Student not found");
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    // ---------- DELETE ----------
    public static void deleteStudent(int id) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = session.get(Student.class, id);

            if (s != null) {
                session.remove(s);
                tx.commit();
                System.out.println("Student deleted!");
            } else {
                System.out.println("Student not found");
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    // ---------- MAIN ----------
    public static void main(String[] args) {
    	factory = new Configuration().configure("hibernate.cgf.xml").buildSessionFactory();
    	
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Create Student");
            System.out.println("2. Get Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    createStudent(name, age);
                    break;

                case 2:
                    System.out.print("Enter student id to fetch: ");
                    int id = sc.nextInt();
                    getStudent(id);
                    break;

                case 3:
                    System.out.print("Enter student id to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new age: ");
                    int newAge = sc.nextInt();

                    updateStudent(uid, newName, newAge);
                    break;

                case 4:
                    System.out.print("Enter student id to delete: ");
                    int did = sc.nextInt();
                    deleteStudent(did);
                    break;

                case 5:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
