package com.cg.otms.service;

import org.springframework.stereotype.Service;

import com.cg.otms.dao.TestDao;
import com.cg.otms.dao.UserDao;
import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class TestService {

	@Autowired
	TestDao testdao;
	
	@Autowired
	UserDao userdao;
	
	
	//Adding Test 
	public Test addTest(Test test)
	{
		return testdao.save(test);
		
	}
	
	
   //Retrieving all Test details from database
	public List<Test> testDetails() {
		return testdao.findAll();
	}
 
	//Update Test
	public Test updateTest(BigInteger testId, Test test)
	{
		if(testdao.existsById(testId))
		{
			Test t=testdao.getOne(testId);
			test.setTestQuestions(t.getTestQuestions());
			 return testdao.save(test);
		}
		else
		{
			return null;
		}
	}
	
	//Delete Test
	public String deleteTest(BigInteger testId)
	{
		if(testdao.existsById(testId))
		{
		  testdao.deleteById(testId);
		  return "deleted successfully";
		}
		else
		{
			return null;
		}
	}

	//Retrieving Test details with particular testId
	 public Optional<Test> getTestById(BigInteger testId) {
			
			return testdao.findById(testId);
		}

	 //Retrieving Question details with particular testId
	public Set<Question> getQuestionById(BigInteger testId) {
		Test t=testdao.getOne(testId);
		return t.getTestQuestions();
	}

	
	//Assigning Test to particular User
	public User assignTest(BigInteger testId, String userId) {
		if(testdao.existsById(testId)&&userdao.existsById(userId))
		{
			Test t=testdao.getOne(testId);
			User u=userdao.getOne(userId);
			t.setUser(u);
			u.setUserTest(t);
			return userdao.save(u);
			
			
		}
		else
		{
		return null;
	}
	}
		
	
	


}
