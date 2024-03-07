package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Employee;
import com.example.helper.Helper;
import com.example.repository.EmployeeRepository;
@Service

public class EmployeeServiceImplement implements EmployeeInterface {

	
@Autowired
 private EmployeeRepository repository;
	@Override

	  public List<String> save(MultipartFile file) {
		List<String> data = new ArrayList<String>();
	        try {
	            List<Employee> employees = Helper.convertExcelToListOfEmployee(file.getInputStream());
	            
	           
	                List<Integer> listOfIdsInDB = repository.findAllIds(); 
	                List<Integer> employeeIdsInExcel = employees.stream().map(Employee::getId).collect(Collectors.toList());
	                
	                List<Integer> deleteRecord = listOfIdsInDB.stream().filter(id -> !employeeIdsInExcel.contains(id)) .collect(Collectors.toList());
	                deleteRecord.forEach((id)->{
	                	 Employee employee  = repository.getEmployee(id);
	                	 if(employee.isActive()== true) {
	                	 employee.setActive(false);
	                	 data.add(String.format("%s Employee active now", employee.getEmployeeName()));
	                	 repository.save(employee);
	                	 }
	                	 
	                });
	            
	            
	            employees.forEach((employee)->{
	            	 Optional<Employee> existingDataOptional = repository.findById(employee.getId());
	            	 if(existingDataOptional.equals(Optional.empty())) {
	            		 employee.setActive(true);
	            		 data.add(String.format("%s Employee inactive now", employee.getEmployeeName()));
	            		 repository.save(employee);
	            		
	            		 
	            	 }
	            	 existingDataOptional.ifPresent(existingData -> {

	            		 if (!areEmployeesEqual(existingData, employee)) {
	            			 employee.setActive(true);
	            			 data.add(String.format("%s Employee updated", employee.getEmployeeName()));
	                         repository.save(employee);
	                        
	                     }
	            		 
	            	 });
	            	
	            
	            });
	            
	            return data;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
			

	    }
	
	private boolean areEmployeesEqual(Employee existingData, Employee employee) {
	    return existingData.getEmployeeName().equals(employee.getEmployeeName()) &&
	           existingData.getEmail().equals(employee.getEmail()) &&
	           existingData.getAddress().equals(employee.getAddress()) &&
	           existingData.getSalary() == employee.getSalary() &&
	           existingData.getBloodGroup().equals(employee.getBloodGroup()) &&
	           existingData.getReportingManager().equals(employee.getReportingManager());
	}

}
