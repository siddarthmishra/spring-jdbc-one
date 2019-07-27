package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/spring_jdbc?useSSL=false&serverTimezone=UTC";
		String user = "springjdbc";
		String pass = "springjdbc";
		Connection myConnection = null;

		try {
			System.out.println("Connecting to database : " + jdbcUrl);

			myConnection = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful : " + myConnection);

			myConnection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
