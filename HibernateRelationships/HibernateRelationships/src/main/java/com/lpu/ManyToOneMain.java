package com.lpu;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lpu.Entity.Department;
import com.lpu.Entity.Employee;

public class ManyToOneMain {

    public static void main(String[] args) {

        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = factory.openSession();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Create Department");
            System.out.println("2. Create Employee");
            System.out.println("3. View Employee");
            System.out.println("4. Update Employee Department");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Department Name: ");
                    String deptName = sc.nextLine();

                    session.beginTransaction();

                    Department dept = new Department(deptName);
                    session.persist(dept);

                    session.getTransaction().commit();
                    System.out.println("Department Created!");
                    break;

                case 2:
                    System.out.print("Enter Employee Name: ");
                    String empName = sc.nextLine();

                    System.out.print("Enter Department ID: ");
                    int deptId = sc.nextInt();

                    Department existingDept =
                            session.get(Department.class, deptId);

                    if (existingDept != null) {

                        session.beginTransaction();

                        Employee emp = new Employee(empName);
                        emp.setDepartment(existingDept);

                        session.persist(emp);

                        session.getTransaction().commit();
                        System.out.println("Employee Created!");

                    } else {
                        System.out.println("Department Not Found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int empId = sc.nextInt();

                    Employee viewEmp =
                            session.get(Employee.class, empId);

                    if (viewEmp != null) {
                        System.out.println("Employee Name: " + viewEmp.getName());

                        if (viewEmp.getDepartment() != null) {
                            System.out.println("Department: " +
                                    viewEmp.getDepartment().getName());
                        }

                    } else {
                        System.out.println("Employee Not Found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    int updateEmpId = sc.nextInt();

                    System.out.print("Enter New Department ID: ");
                    int newDeptId = sc.nextInt();

                    Employee updateEmp =
                            session.get(Employee.class, updateEmpId);

                    Department newDept =
                            session.get(Department.class, newDeptId);

                    if (updateEmp != null && newDept != null) {

                        session.beginTransaction();

                        updateEmp.setDepartment(newDept);

                        session.getTransaction().commit();

                        System.out.println("Department Updated!");

                    } else {
                        System.out.println("Employee or Department Not Found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Employee ID: ");
                    int deleteEmpId = sc.nextInt();

                    Employee deleteEmp =
                            session.get(Employee.class, deleteEmpId);

                    if (deleteEmp != null) {

                        session.beginTransaction();

                        session.remove(deleteEmp);

                        session.getTransaction().commit();

                        System.out.println("Employee Deleted!");

                    } else {
                        System.out.println("Employee Not Found!");
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
