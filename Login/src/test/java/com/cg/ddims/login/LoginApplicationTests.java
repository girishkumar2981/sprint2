package com.cg.ddims.login;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ddims.login.dto.Login;
import com.cg.ddims.login.service.LoginService;

@SpringBootTest
public class LoginApplicationTests {
@Autowired
LoginService loginService;
	@Test
	public void Login_Positive() throws Exception
	{
		Optional<Login> login = loginService.getLoginDetails(12345678);
		Assertions.assertEquals(true, login.isPresent());
	}
	@Test
	public void Login_Negative() throws Exception
	{
		Optional<Login> login = loginService.getLoginDetails(123456789);
		Assertions.assertEquals(false, login.isPresent());
	}
	
}
