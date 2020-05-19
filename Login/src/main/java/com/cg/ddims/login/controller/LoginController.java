package com.cg.ddims.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.ddims.login.dto.Login;
import com.cg.ddims.login.service.LoginService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController 
public class LoginController {
	@Autowired
	LoginService loginService;
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	@Autowired
	RestTemplate rest;
	/*
	 * This method is used to check whether enter credentials are valid are not using HTTP Post method
	 */
	@PostMapping(value="/login/userId/{userId}/password/{password}")
	public ResponseEntity<Login> getLoginDetails(@PathVariable long userId,@PathVariable String password)
	{
		try
		{
			Optional<Login> login = loginService.getLoginDetails(userId);
			if(login.isPresent())
			{
				if(login.get().getUserId() == userId && login.get().getPassword().equals(password))
				{
					return new ResponseEntity<>(login.get(),HttpStatus.OK);
				}
				else
					return new ResponseEntity("UserId/Password Invalid",HttpStatus.NOT_FOUND);
			}
			else
				return new ResponseEntity("UserId not found",HttpStatus.NOT_FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity("UserId not found",HttpStatus.NOT_FOUND);
		}
		
	}
	

}


