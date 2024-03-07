package com.example.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.helper.Helper;
import com.example.service.EmployeeServiceImplement;

@RestController
@RequestMapping("/api/excel")
public class EmployeeExcelController {
	
	@Autowired
	 private EmployeeServiceImplement employeeService;

	    @PostMapping
	    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {

	    	 if (Helper.checkExcelFormat(file)) {
	             //true
   
	    		 List<String> messages=employeeService.save(file);

	             return ResponseEntity.ok(Map.of("message", messages));


	         }
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	    }
	    
	    

}


