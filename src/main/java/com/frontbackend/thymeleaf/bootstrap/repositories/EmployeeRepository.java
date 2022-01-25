package com.frontbackend.thymeleaf.bootstrap.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.frontbackend.thymeleaf.bootstrap.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query(value = "select e from Employee e where e.nome like %:search% or e.local like %:search% ")
	Page<Employee> findByAlgo(@Param("search") String search, Pageable pageable);

}
