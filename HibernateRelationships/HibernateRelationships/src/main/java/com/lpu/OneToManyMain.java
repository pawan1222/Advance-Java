
package com.lpu;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lpu.Entity.Customer;
import com.lpu.Entity.Order;

public class OneToManyMain {

    public static void main(String[] args) {

        // Create SessionFactory
        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Open Session
        Session session = factory.openSession();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Create Customer");
            System.out.println("2. Add Order to Customer");
            System.out.println("3. View Customer");
            System.out.println("4. Update Customer Name");
            System.out.println("5. Delete Customer");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // 1️⃣ CREATE CUSTOMER
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();

                    session.beginTransaction();

                    Customer customer = new Customer(name);
                    session.persist(customer);

                    session.getTransaction().commit();

                    System.out.println("Customer Created!");
                    break;

                // 2️⃣ ADD ORDER
                case 2:
                    System.out.print("Enter Customer ID: ");
                    int custId = sc.nextInt();
                    sc.nextLine();

                    Customer existingCustomer =
                            session.get(Customer.class, custId);

                    if (existingCustomer != null) {

                        System.out.print("Enter Product Name: ");
                        String product = sc.nextLine();

                        session.beginTransaction();

                        Order order = new Order(product);
                        existingCustomer.addOrder(order);

                        session.persist(existingCustomer);

                        session.getTransaction().commit();

                        System.out.println("Order Added!");
                    } else {
                        System.out.println("Customer Not Found!");
                    }
                    break;

                // 3️⃣ VIEW CUSTOMER
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int viewId = sc.nextInt();

                    Customer viewCustomer =
                            session.get(Customer.class, viewId);

                    if (viewCustomer != null) {

                        System.out.println("Customer: " + viewCustomer.getName());

                        List<Order> orders = viewCustomer.getOrders();
                        for (Order o : orders) {
                            System.out.println("Order: " + o.getProductName());
                        }

                    } else {
                        System.out.println("Customer Not Found!");
                    }
                    break;

                // 4️⃣ UPDATE CUSTOMER
                case 4:
                    System.out.print("Enter Customer ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Customer updateCustomer =
                            session.get(Customer.class, updateId);

                    if (updateCustomer != null) {

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        session.beginTransaction();

                        updateCustomer.setName(newName);

                        session.getTransaction().commit();

                        System.out.println("Customer Updated!");
                    } else {
                        System.out.println("Customer Not Found!");
                    }
                    break;

                // 5️⃣ DELETE CUSTOMER
                case 5:
                    System.out.print("Enter Customer ID: ");
                    int deleteId = sc.nextInt();

                    Customer deleteCustomer =
                            session.get(Customer.class, deleteId);

                    if (deleteCustomer != null) {

                        session.beginTransaction();

                        session.remove(deleteCustomer);

                        session.getTransaction().commit();

                        System.out.println("Customer Deleted!");
                    } else {
                        System.out.println("Customer Not Found!");
                    }
                    break;

                case 6:
                    session.close();
                    factory.close();
                    System.out.println("Application Closed!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
