package com.cg.otms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Admin;
import com.cg.otms.exception.AdminDefinedException;
import com.cg.otms.service.AdminService;

@RestController                       
@RequestMapping("/admin")             
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
@Autowired                      
AdminService adminservice; 

	/**
	 * This method used for adminLogin 
	 * @return String valid admin if admin details are present
	 */
    @GetMapping("/adminLogin/{adminId},{adminPassword}")
    public String adminLogin(@PathVariable("adminId") String adminId,@PathVariable("adminPassword") String adminPassword) {
	Optional<Admin> adminDetails = adminservice.adminLogin(adminId,adminPassword);
	
	return adminDetails.toString();
}
	
	/**
	 * This method used for adding admin details into database
	 */
	@PostMapping("/addAdmin")                 
	public void addAdmin()
	{
		adminservice.addAdmin();         //Invoking a method - addAdmin
	}
	
   @ExceptionHandler(AdminDefinedException.class)
	public ResponseEntity<String> adminNotFound(AdminDefinedException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
