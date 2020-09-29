package com.cg.otms.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;
import com.cg.otms.exception.IdNotFoundException;
import com.cg.otms.service.QuestionService;


@RestController                             
@RequestMapping("/testquestions")            
@CrossOrigin(origins = "http://localhost:4200")       
public class QuestionController {
	
@Autowired                                   
QuestionService questionservice;             

 	/**
 	 * This method used to add question by Id 
 	 * @return String Question added successfully
 	 */
	@PostMapping("/addQuestion/{testId}")
	public ResponseEntity<String> addQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Test testDetails = questionservice.addQuestion(testId,question); //invoking a method - addQuestion
		//checking whether the object is null 
		
		if (testDetails == null) {
			throw new IdNotFoundException("QuestionId already exists");
		} else {
			//returning the ResponseEntity<String> with httpStatus and httpHeaders
			return new ResponseEntity<String>("Question  added successfully", new HttpHeaders(), HttpStatus.OK);
		}
		
	}
		
	
	/**
	 * This method used to delete the question with particular questionId
	 * @return String that Question Details Deleted Successfully        
	 */
	@PostMapping("/deleteQuestion/{testId}")
	private ResponseEntity<String> deleteQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Boolean status = questionservice.deleteQuestion(testId,question);
		if (status == false) {
			throw new IdNotFoundException("Delete operation is unsuccessful");
		
		} else {
			return new ResponseEntity<String>("Delete operation is successful", new HttpHeaders(), HttpStatus.OK);
		
	}
	}
	
	    /**
		 * This method used to calculate total marks 
		 * @return testDetails        
		 */
		@PostMapping("/calculateTotalMarks")
		public Test calculateTotalMarks(@RequestBody Test test) {
			Test testDetails = questionservice.calculateTotalMarks(test);
			if (testDetails == null) {

				throw new IdNotFoundException("Test details not found");
			}
			else
			{
			return testDetails;
			}
		}
		

		 /**
		 * This method used to update Question
		 * @return String Question updated successfully       
		 */
		@PostMapping("/updateQuestion/{testId}")
		public ResponseEntity<String> updateQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
			Question questionDetails = questionservice.updateQuestion(testId,question);
			if (questionDetails == null) {
				throw new IdNotFoundException("Update Operation Unsuccessful,Provided testId does not exist");
			
			} else {
				return new ResponseEntity<String>("Question updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
	//Exception Handling
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}