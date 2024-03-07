package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	 @Query("SELECT e.id FROM Employee e")
	List<Integer> findAllIds();
	 

	 @Query("SELECT e FROM Employee e WHERE e.id = :id")
	    Employee getEmployee(@Param("id") Integer id);
}
