package com.cg.otms;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.otms.dto.User;
import com.cg.otms.service.TestService;

@SpringBootTest
class OnlineTestManagementSystemApplicationTests {
	@Autowired
	TestService testservice;
	@Test
	public void testAssignTest_Positive()
	{
		BigInteger b1;
		 b1 = new BigInteger("111");
		User user = testservice.assignTest(b1,"student1");
		Assertions.assertNotNull(user);
	}
	
	@Test
	public void testAssignTest_Negative()
	{
		BigInteger b2;
		 b2 = new BigInteger("111");
		User user = testservice.assignTest(b2,"student10");
		Assertions.assertNull(user);
	}
	
	
	@Test
	public void testDeleteTest_Positive()
	{
		BigInteger b3;
		 b3 = new BigInteger("111");
		String s = testservice.deleteTest(b3);
		Assertions.assertEquals("deleted successfully",s);
	}

	@Test
	public void testDeleteTest_Negative()
	{
		BigInteger b4;
		 b4 = new BigInteger("250");
		String s = testservice.deleteTest(b4);
		Assertions.assertNull(s);
	}
	
}
