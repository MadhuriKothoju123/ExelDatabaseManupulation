package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;




public interface EmployeeInterface {
	  
	  public List<String> save(MultipartFile file);

}
