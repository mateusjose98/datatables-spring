package com.frontbackend.thymeleaf.bootstrap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.frontbackend.thymeleaf.bootstrap.model.Employee;
import com.frontbackend.thymeleaf.bootstrap.repositories.EmployeeRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private EmployeeRepository repository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		for (int i = 0; i <= 100; i++) {
			Employee e1 = Employee.builder().nome("Mateus" + i).local("Escritorio tal" + i).build();
			repository.save(e1);

		}

	}
}
