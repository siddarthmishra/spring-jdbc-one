package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.daoImpl.StudentImpl;
import com.pojo.Student;

public class StudentMain {

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				com.configurations.SpringJdbcConfig.class);
		StudentImpl studentImpl = context.getBean("myStudentImpl", StudentImpl.class);
		Scanner sc = new Scanner(System.in);
		int ch, id;
		String fname, lname, email;
		Student student = new Student();
		final int EXIT = 0;
		do {
			System.out.println(
					"1.Enroll Student\n2.Update Particular Student\n3.Get Particular Student Details\n4.Delete Particular Student\n5.Get all Students");
			System.out.println(EXIT + ".Exit\nEnter the option from above");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("\nEnroll Student");
				System.out.println("Enter First Name : ");
				fname = sc.next();
				System.out.println("Enter Last name : ");
				lname = sc.next();
				System.out.println("Enter Email : ");
				email = sc.next();
				student = new com.pojo.Student(fname, lname, email);
				studentImpl.save(student);
				break;
			case 2:
				System.out.println("\nUpdate Student");
				System.out.println("Enter Student ID to be updated : ");
				id = sc.nextInt();
				studentImpl.updateById(id);
				break;
			case 3:
				System.out.println("\nEnter Student ID to get the details : ");
				id = sc.nextInt();
				System.out.println(studentImpl.getById(id) + "\n");
				break;
			case 4:
				System.out.println("\nEnter Student ID to be deleted : ");
				id = sc.nextInt();
				studentImpl.deleteById(id);
				break;
			case 5:
				studentImpl.getAll().stream().forEach(System.out::println);
				System.out.println();
				break;
			case EXIT:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid Selection. Enter again\n");
			}

		} while (ch != EXIT);
		System.out.println("Thank You");
		sc.close();
		context.close();
	}

}
