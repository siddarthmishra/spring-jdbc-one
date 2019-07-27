package com.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.daoImpl.StudentImpl;

@Configuration
public class SpringJdbcConfig {
	@Bean
	public DataSource mySqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("springjdbc");
		dataSource.setPassword("springjdbc");
		return dataSource;
	}

	@Bean
	public JdbcTemplate myJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(mySqlDataSource());
		return jdbcTemplate;
	}

	@Bean
	public StudentImpl myStudentImpl() {
		StudentImpl studentImpl = new StudentImpl(myJdbcTemplate());
		return studentImpl;
	}
}