package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.pojo.Student;

public interface StudentDAO {

	// Create
	public void save(Student student) throws SQLException;

	// Read
	public Student getById(int id);

	// Update
	public void updateById(int id);

	// Delete
	public void deleteById(int id);

	// Get All
	public List<Student> getAll();
}