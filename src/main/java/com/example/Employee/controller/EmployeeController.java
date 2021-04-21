package com.example.Employee.controller;



import brave.sampler.Sampler;
import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

	@GetMapping(value="/getAllEmp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employees>> getAllEmployees() {
		System.out.println("hello");
		return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAll());
	}

	/*	@GetMapping("/employee")
	public ResponseEntity<Employees> getAllEmployee(){
		return employeeRepository.findAll();
	 } */

	@GetMapping("/getEmp/{id}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employees employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/postEmp")
	public Employees createEmployee(@Valid @RequestBody Employees employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/putEmp/{id}")
	public ResponseEntity<Employees> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employees employeeDetails) throws ResourceNotFoundException {
		Employees employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employees updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/deleteEmp/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employees employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}