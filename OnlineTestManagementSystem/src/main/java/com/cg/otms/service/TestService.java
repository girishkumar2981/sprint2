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
		
	/**
	 * Adding the test into the database
	 */
	public Test addTest(Test test)
	{
		return testdao.save(test);           //saves the given entity
	}
	
	/**
	 * Returning the list of all test objects
	 */
	public List<Test> testDetails() {
		return testdao.findAll();           //returns all the instances of the type
	}
	
	/**
	 * Deleting the test with testId
	 */
	public String deleteTest(BigInteger testId)
	{
		
		if(testdao.existsById(testId))       //Returns whether an entity with the given id exists. 
		{
		  testdao.deleteById(testId);          //Deletes the entity with the given id.
		  return "deleted successfully";
		}
		else
		{
			return null;
		}
	}

	/**
	 * Retrieving test details by testId
	 */
	 public Optional<Test> getTestById(BigInteger testId) {
			
			return testdao.findById(testId);        //Retrieves an entity by its id.
		}

	   /**
		 * Retrieving question details by questionId
		 */
	public Set<Question> getQuestionById(BigInteger testId) {
		Test t=testdao.getOne(testId);           //Returns a reference to the entity with the given identifier
		return t.getTestQuestions();
	}

	
	/**
	 * Assigning test by testTd and userId
	 */
	public User assignTest(BigInteger testId, String userId) {
		//Condition : To check whether test and user exists by id
		if(testdao.existsById(testId)&&userdao.existsById(userId))
		{
			Test t=testdao.getOne(testId); 
			User u=userdao.getOne(userId);  //Returns a reference to the entity with the given identifier
			t.setUser(u);
			u.setUserTest(t);
			return userdao.save(u);    //Saves a given entity
			
			
		}
		else
		{
		return null;
	}
	}
	
	/**
	 * Updating test 
	 */
		public Test updateTest(BigInteger testId, Test test)
		{
			//Condition : To check whether test exists by Id
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
		
	
	


}
