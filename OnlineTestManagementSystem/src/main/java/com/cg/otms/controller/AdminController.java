package com.cg.otms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Admin;
import com.cg.otms.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
@Autowired
AdminService adminservice;
//Admin login method

	@RequestMapping("/adminLogin/{adminId},{adminPassword}")
	public String adminLogin(@PathVariable("adminId") String adminId,@PathVariable("adminPassword") String adminPassword) {
		Optional<Admin> adminDetails = adminservice.adminLogin(adminId,adminPassword);
		if(adminDetails.isPresent())
		{
			return "valid";
		}
		else
		{
		return "invalid";
		}
	}
	
	//Inserting Admin details into database
	@RequestMapping("/addAdmin")
	public void addAdmin()
	{
		adminservice.addAdmin();
	}
}
