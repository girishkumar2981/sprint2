package com.cg.otms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import com.cg.otms.exception.UserDefinedException;
import com.cg.otms.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
@Autowired
UserService userservice;
    //user login method
	@GetMapping("/UserLogin/{userId}/{password}")
	public String userLogin(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		Optional<User> userDetails = userservice.userLogin(userId,password);
		
		return userDetails.toString();
	}
	
	//Retrieving test details of particular User
	@GetMapping("/UserTest/{userId}")
	public Test userTest(@PathVariable("userId") String userId) {
		Test testDetails = userservice.userTest(userId);
   if (testDetails==null) {
			
			throw new UserDefinedException("Test is not assigned to the User");
		}
   else
   {
		return testDetails;
   }
	}
}
