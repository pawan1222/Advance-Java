package com.lpu;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lpu.Entity.Passport;
import com.lpu.Entity.Person;

public class OneToOneMain {

    public static void main(String[] args) {

        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n====== PERSON - PASSPORT MENU ======");
            System.out.println("1. Create Person with Passport");
            System.out.println("2. Get Person By ID");
            System.out.println("3. Get All Persons");
            System.out.println("4. Update Person Name");
            System.out.println("5. Delete Person");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Person Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Passport Number: ");
                    String pno = sc.nextLine();

                    System.out.print("Enter Country: ");
                    String country = sc.nextLine();

                    createPerson(factory, name, pno, country);
                    break;

                case 2:
                    System.out.print("Enter Person ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    getPersonById(factory, id);
                    break;

                case 3:
                    getAllPersons(factory);
                    break;

                case 4:
                    System.out.print("Enter Person ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    updatePerson(factory, updateId, newName);
                    break;

                case 5:
                    System.out.print("Enter Person ID to Delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    deletePerson(factory, deleteId);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        factory.close();
        sc.close();
    }

    // ================== CREATE ==================
    public static void createPerson(SessionFactory factory,
                                    String name,
                                    String passportNumber,
                                    String country) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Passport passport = new Passport();
            passport.setPassportNumber(passportNumber);
            passport.setCountry(country);

            Person person = new Person();
            person.setName(name);
             person.setPassport(passport);   // IMPORTANT mapping

            session.persist(person);  // Cascade will save passport too
            tx.commit();

            System.out.println("Person & Passport saved successfully!");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ================== READ BY ID ==================
    public static void getPersonById(SessionFactory factory, int id) {

        Session session = factory.openSession();

        try {
            Person person = session.get(Person.class, id);

            if (person != null) {
                System.out.println("Person ID: " + person.getPersonId());
                System.out.println("Name: " + person.getName());

                Passport p = person.getPassport();
                if (p != null) {
                    System.out.println("Passport No: " + p.getPassportNumber());
                    System.out.println("Country: " + p.getCountry());
                }

            } else {
                System.out.println("Person not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ================== READ ALL ==================
    public static void getAllPersons(SessionFactory factory) {

        Session session = factory.openSession();

        try {
            List<Person> list =
                    session.createQuery("from Person", Person.class).getResultList();

            for (Person person : list) {
                System.out.println("\nID: " + person.getPersonId());
                System.out.println("Name: " + person.getName());

                Passport p = person.getPassport();
                if (p != null) {
                    System.out.println("Passport: " + p.getPassportNumber());
                    System.out.println("Country: " + p.getCountry());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ================== UPDATE ==================
    public static void updatePerson(SessionFactory factory, int id, String newName) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Person person = session.get(Person.class, id);

            if (person != null) {
                person.setName(newName);
                tx.commit();
                System.out.println("Person updated successfully!");
            } else {
                System.out.println("Person not found!");
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ================== DELETE ==================
    public static void deletePerson(SessionFactory factory, int id) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Person person = session.get(Person.class, id);

            if (person != null) {
                session.remove(person);  // Cascade deletes passport also
                tx.commit();
                System.out.println("Person deleted successfully!");
            } else {
                System.out.println("Person not found!");
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
