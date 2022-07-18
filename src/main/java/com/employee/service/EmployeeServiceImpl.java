package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee save(Employee emp) {

		return repo.save(emp);
	}

	@Override
	public List<Employee> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public void deleteById(Integer id) {

		repo.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public Employee update(Employee emp) {

		return repo.save(emp);
	}

}
