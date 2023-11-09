package com.task.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.task.backend.entities.Employee;
import com.task.backend.service.ExcelSaveService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	ExcelSaveService service;
	
	@GetMapping("/allEmployees")
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
		List<Employee> emps=service.getAllEmployees();
		return new ResponseEntity<>(emps,HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> saveExcelOfEmployee(@RequestParam("file") MultipartFile file)
	{
		try
		{
			service.save(file);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return ResponseEntity.ok(Map.of("message","Saved successfully to DB"));
	}
}
