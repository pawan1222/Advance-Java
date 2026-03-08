package com.lpu;

import org.hibernate.cfg.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.lpu.entity.Passport;
import com.lpu.entity.Person;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            Passport passport = new Passport("IND123456");

            Person person = new Person();
            person.setName("Pawan"); 
            person.setPassport(passport);

            // Important for bidirectional mapping
            passport.setPerson(person);

            session.persist(person);

            transaction.commit();

            System.out.println("Data inserted successfully!");

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
