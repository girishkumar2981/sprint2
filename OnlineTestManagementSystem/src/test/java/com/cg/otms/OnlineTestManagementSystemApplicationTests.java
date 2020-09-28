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
	//Testing assignTest method -Positive test case
	@Test
	public void testAssignTest_Positive()
	{
		BigInteger b1;
		 b1 = new BigInteger("111");
		User user = testservice.assignTest(b1,"student1");  //Invoking a method -assignTest
		Assertions.assertNotNull(user);                     //Test case passes when user is not null
	}
	//Testing assignTest method -Negative test case
	@Test
	public void testAssignTest_Negative()
	{
		BigInteger b2;
		 b2 = new BigInteger("111");
		User user = testservice.assignTest(b2,"student10");  //Invoking a method -assignTest
		Assertions.assertNull(user);                         //Test case passes when user is null
	}
	
	//Testing DeleteTest method -Positive test case
	@Test
	public void testDeleteTest_Positive()
	{
		BigInteger b3;
		 b3 = new BigInteger("111");
		String s = testservice.deleteTest(b3);               //Invoking a method -deleteTest
		Assertions.assertEquals("deleted successfully",s);   
	}
	//Testing DeleteTest method -Negative test case
	@Test
	public void testDeleteTest_Negative()
	{
		BigInteger b4;
		 b4 = new BigInteger("250");
		String s = testservice.deleteTest(b4);          //Invoking a method -deleteTest
		Assertions.assertNull(s);                       //Test case passes when s is null
	}
	
}
