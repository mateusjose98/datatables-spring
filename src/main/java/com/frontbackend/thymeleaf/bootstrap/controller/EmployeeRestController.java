package com.frontbackend.thymeleaf.bootstrap.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frontbackend.thymeleaf.bootstrap.repositories.EmployeeRepository;
import com.frontbackend.thymeleaf.bootstrap.service.EmpregadoDataTablesService;

@RestController
@RequestMapping("employees")
public class EmployeeRestController {


    private final EmployeeRepository rep;

    @Autowired
    public EmployeeRestController(EmployeeRepository rep) {

        this.rep = rep;
    }
    
 
    @GetMapping
    public ResponseEntity<?> datatables(HttpServletRequest request){
    	Map<String, Object> data = new EmpregadoDataTablesService().execute(rep, request);
    	return ResponseEntity.ok(data);
    }
    

 

}
