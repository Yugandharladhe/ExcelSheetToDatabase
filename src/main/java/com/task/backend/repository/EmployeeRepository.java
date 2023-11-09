package com.task.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.backend.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
