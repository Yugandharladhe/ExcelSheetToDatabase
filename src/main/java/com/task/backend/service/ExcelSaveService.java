package com.task.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.task.backend.entities.Employee;
import com.task.backend.repository.EmployeeRepository;
import com.task.backend.util.ExcelSheetToObject;

@Service
public class ExcelSaveService {
	
	@Autowired
	ExcelSheetToObject obj;
	
	@Autowired 
	EmployeeRepository repo;
	
	public void save(MultipartFile file) throws IOException
	{
		List<Employee> emps=obj.getListOfObjects(file.getInputStream());
		repo.saveAll(emps);
	}
	
	public List<Employee> getAllEmployees()
	{
		return repo.findAll();
	}
}
