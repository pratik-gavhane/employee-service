package com.msp.employeeService.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msp.employeeService.service.EmployeeService;
import com.msp.employeeService.util.EmployeeResponse;

@RestController
public class EmployeeController {
	
	private EmployeeService empService;
	
	public EmployeeController(EmployeeService empService) {
		this.empService = empService;
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("id") int id) {
		
		EmployeeResponse empRes = empService.getEmpById(id);
		return ResponseEntity.status(HttpStatus.OK).body(empRes) ;		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<String> saveEmployeeDetails(@RequestBody EmployeeResponse emp){
		empService.saveEmployeedetails(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body("Employee details saved");
	}
	
	@GetMapping("/employees")
	public  List<EmployeeResponse> getAllEmployees(){
		return empService.getEmployeeList();
	}
	
	
	
	
	
	
	/*
	 * @ExceptionHandler public ResponseEntity<CustomErrorResponse>
	 * handleException(Exception exc){ CustomErrorResponse response = new
	 * CustomErrorResponse();
	 * if(exc.getClass().getSimpleName().equalsIgnoreCase("NosuchElementException"))
	 * { response.setStatus("404"); response.setDescription(exc.getMessage()); }
	 * return new ResponseEntity<CustomErrorResponse>(response,
	 * HttpStatus.NOT_FOUND); }
	 */

}
