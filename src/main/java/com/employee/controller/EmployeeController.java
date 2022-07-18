package com.employee.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exceptions.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	/*
	 * @Autowired private final Logger LOGGER =
	 * LoggerFactory.getLogger(EmployeeController.class);
	 */
	
	@PostMapping("/save")
	public Employee saveEmployee(@Valid @RequestBody Employee emp) {
		//LOGGER.info("Added an Employee: "+ emp);
		return service.save(emp);
	}
	
	@GetMapping("/getall")
	public List<Employee> getAllEmployees() {
		//LOGGER.info("Get all an Employees: ");
		return service.findAll();

	}

	@GetMapping("/get/{id}")
	public Employee getById(@PathVariable Integer id) throws EmployeeNotFoundException {

		//LOGGER.info("Get an Employees: ");
		Optional<Employee> emp= service.findById(id);
		if(!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with the id");
		}else {
			return emp.get();
		}

	}

	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteById(@PathVariable Integer id) {

		//LOGGER.info("Delete Employees: ");
		if (service.findById(id).isPresent()) {
			service.deleteById(id);
			return HttpStatus.ACCEPTED;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

	@DeleteMapping("/delete/all")
	public HttpStatus deleteAll() {

		//LOGGER.info("Delete all Employees: ");
		service.deleteAll();
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee emp) {

		//LOGGER.info("Update an Employees: ");
		if (service.findById(id).isPresent()) {
			emp.setId(id);
			service.save(emp);
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
