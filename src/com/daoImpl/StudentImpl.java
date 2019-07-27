package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dao.StudentDAO;
import com.mapper.StudentMapper;
import com.pojo.Student;

public class StudentImpl implements StudentDAO {

	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public StudentImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Student student) throws SQLException {
		System.out.println("Enrolling : " + student);
		String SQL = "insert into student(first_name, last_name, email) values(?,?,?)";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, student.getFirstName());
				ps.setString(2, student.getLastName());
				ps.setString(3, student.getEmail());
				return ps;
			}
		}, generatedKeyHolder);
		student.setId(generatedKeyHolder.getKey().intValue());
		System.out.println("Successfully enrolled : " + student + "\n");
	}

	@Override
	public Student getById(int id) {
		String SQL = "select * from student where id = ?";
		List<Student> student = jdbcTemplate.query(SQL, new StudentMapper(), id);
		return student.size() == 1 ? student.get(0) : new Student();
	}

	@Override
	public void updateById(int id) {
		Student originalStudent = getById(id);
		if (originalStudent.getId() != 0) {
			System.out.println("Before Update : " + originalStudent);
			String SQL = "update student set first_name = ?, last_name = ?, email = ? where id = ?";
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Updated First Name : ");
			String fname = sc.next();
			System.out.println("Enter Updated Last name : ");
			String lname = sc.next();
			System.out.println("Enter Updated Email : ");
			String email = sc.next();
			int i = jdbcTemplate.update(SQL, fname, lname, email, id);
			if (i == 1) {
				System.out.println("Successfully Updated Student with ID " + id);
				System.out.println("Student details after update : " + getById(id) + "\n");
			} else
				System.out.println("FAILED\n");
		} else
			System.out.println("Student with ID " + id + " not found\n");
	}

	@Override
	public void deleteById(int id) {
		String SQL = "delete from student where id = ?";
		int i = jdbcTemplate.update(SQL, id);
		if (i == 1)
			System.out.println("Student with ID " + id + " successfully deleted\n");
		else
			System.out.println("Student with ID " + id + " not found\n");
	}

	@Override
	public List<Student> getAll() {
		String SQL = "select * from student";
		List<Student> students = jdbcTemplate.query(SQL, new StudentMapper());
		if (students.size() == 0)
			System.out.println("No Data Found...\n");
		return students;
	}

}
