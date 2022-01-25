package com.frontbackend.thymeleaf.bootstrap.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.frontbackend.thymeleaf.bootstrap.model.Employee;
import com.frontbackend.thymeleaf.bootstrap.repositories.EmployeeRepository;

public class EmpregadoDataTablesService {

	private String[] cols = { "id", "nome", "local" };

	public Map<String, Object> execute(EmployeeRepository rep, HttpServletRequest req) {

		int start = Integer.parseInt(req.getParameter("start"));
		int length = Integer.parseInt(req.getParameter("length"));
		int draw =  Integer.parseInt(req.getParameter("draw"));
		String search = searchBy(req);
		int current = currentPage(start, length);

		String column = columnName(req);
		Sort.Direction direction = orderBy(req);
		
		Pageable pageable = PageRequest.of(current, length, direction, column);
		
		Page<Employee> page = queryBy(search, rep, pageable);
		
		Map<String, Object> json = new LinkedHashMap<>();

		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());

		return json;
	}

	private Page<Employee> queryBy(String search, EmployeeRepository rep, Pageable pageable) {
		return search.isEmpty() ? rep.findAll(pageable) : rep.findByAlgo(search, pageable);
	}
	
private String searchBy(HttpServletRequest request) {
		
		return request.getParameter("search[value]").isEmpty()
				? ""
				: request.getParameter("search[value]");
	}

	private Direction orderBy(HttpServletRequest req) {
		String order = req.getParameter("order[0][dir]");
		return order.equals("desc") ? Sort.Direction.DESC  :  Sort.Direction.ASC;
	}

	private String columnName(HttpServletRequest req) {
		int iCol = Integer.parseInt(req.getParameter("order[0][column]"));
		return cols[iCol];
	}

	private int currentPage(int start, int length) {
		
		return start / length;
	}
}
