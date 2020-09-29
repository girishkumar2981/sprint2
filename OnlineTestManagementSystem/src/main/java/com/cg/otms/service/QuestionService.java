package com.cg.otms.service;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.otms.dao.QuestionDao;
import com.cg.otms.dao.TestDao;
import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;

@Service                           
@Transactional                      
public class QuestionService {
@Autowired                          
TestDao testdao;                    
@Autowired                          
QuestionDao questiondao;            

	/**
	 * Adding the question to a test 
	 */
	public Test addQuestion(BigInteger testId,Question question)
	{
	if(testdao.existsById(testId)&&!(questiondao.existsById(question.getQuestionId())))
	{
		Test t=testdao.getOne(testId);       //Returns a reference to the entity with the given identifier
		question.setTest(t);                 
		t.getTestQuestions().add(question);  //adding questions
		t.setTestTotalMarks(t.getTestTotalMarks()+question.getQuestionMarks());  
		
		return testdao.save(t);              //saves the given entity
	}
	else
	{
    return null;
	}
	}
	
	/**
	 * Deleting the question by questionId
	 */
	public boolean  deleteQuestion(BigInteger testId,Question question)
	{
		if(testdao.existsById(testId))
		{
			
			 questiondao.delete(question);;
			return true;
		
		}
		else
		{
	      return false;
		} 
		
	}

	 
	 /**
	  * updating the question 
	  */
		public Question updateQuestion(BigInteger testId,Question question)
		{if(testdao.existsById(testId))
		{
			Test t=testdao.getOne(testId);
			question.setTest(t);
			return questiondao.save(question);
		}
		else
		{
	      return null;
		}
		}
		
		/**
		  * calculating total marks
		  */
		public Test calculateTotalMarks(Test test) {
		     Set<Question> s=test.getTestQuestions();
		     int testTotalMarks=0;
		     int testMarksScored=0;
		     Iterator<Question> it = s.iterator(); 
		     while (it.hasNext()) 
		     {
		          Question q= it.next(); 
		          
		          if(q.getChoosenAnswer()==q.getQuestionAnswer())
		          {
		        	  q.setMarksScored(q.getQuestionMarks());
		          }
		          testTotalMarks=testTotalMarks+q.getQuestionMarks();
		          testMarksScored=testMarksScored+q.getMarksScored();
		          q.setTest(test);
		          questiondao.save(q);
			       
		     }
			test.setTestTotalMarks(testTotalMarks);
			test.setTestMarksScored(testMarksScored);
		     
			return test;
		}

	}

