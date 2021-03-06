
package com.cg.otms.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.otms.dto.Question;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
import com.cg.otms.exception.IdNotFoundException;
import com.cg.otms.service.TestService;

@RestController                             
@RequestMapping("/test")                    
@CrossOrigin(origins = "http://localhost:4200")      
public class TestController {

@Autowired                  
	
TestService testservice;   
	
	  /**
	  * This method used to add test 
	  * @return String Test added successfully
	  */
		@PostMapping("/addTest")            
		public ResponseEntity<String> addTest(@RequestBody Test test) {
			
			Test testDetails = testservice.addTest(test);    //Invoking a method - addTest
			//Condition - Checking whether the obtained object is null
			if (testDetails == null) {

				throw new IdNotFoundException("TestID already exists"); 

			} else {
				//returning the ResponseEntity<String> with httpStatus and httpHeaders
				return new ResponseEntity<String>("Test added successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		/**
		 * This method used to get test by Id

		 * @return testDetails
		 */
		@GetMapping("/getTest/{testId}")            
		public ResponseEntity<Optional<Test>> getTestById(@PathVariable("testId") BigInteger testId) {
			
			
			Optional<Test> testDetails = testservice.getTestById(testId); //Invoking a method - getTestById
			//Condition - Checking whether the obtained object is null
			if (!testDetails.isPresent()) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
			} else {
				//returning the testDetails with httpStatus and httpHeaders
				return new ResponseEntity<Optional<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		/**
		 * This method used to assign a test to user

		 * @return String telling that Test assigned successfully
		 */
		@GetMapping("/assignTest/{testId}/{userId}")      
		public ResponseEntity<String> assignTest(@PathVariable("testId") BigInteger testId,@PathVariable("userId") String userId) {
			
			User userDetails = testservice.assignTest(testId,userId);  //Invoking a method- assignTest
			//Condition - Checking whether the obtained object is null
			if (userDetails == null) {
				throw new IdNotFoundException("Test is already assigned to other user or credentials are incorrect");
	          		
			} else {
				//returning the ResponseEntity<String> with httpStatus and httpHeaders
				return new ResponseEntity<String>("Test assigned successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		/**
		 * This method used to retrieve questions by Id

		 * @return questionDetails
		 */
		@GetMapping("/getquestions/{testId}")            
		public ResponseEntity<Set<Question>> getQuestionById(@PathVariable("testId") BigInteger testId) {
			
			Set<Question> questionDetails = testservice.getQuestionById(testId); //Invoking a method- getQuestionById
			//Condition - Checking whether the obtained object is empty
			if (questionDetails.isEmpty()) {
				throw new IdNotFoundException("questions are not assigned");  
			} else {
				//returning the questionDetails with httpStatus and httpHeaders
				return new ResponseEntity<Set<Question>>(questionDetails, new HttpHeaders(), HttpStatus.OK);
			}
			
		}
		
		/**
		 * This method used to retrieve all testDetails By Id

		 * @return testDetails
		 */
		@GetMapping("/testdetails")             
		public ResponseEntity<List<Test>> testDetails(){ 
			
			List<Test> testDetails=testservice.testDetails(); //Invoking a method- testDetails
			//Condition - Checking whether the obtained object is empty
			if (testDetails.isEmpty()) {
				throw new IdNotFoundException("No Tests available");  
			} else {
				//returning the testDetails with httpStatus and httpHeaders
				return new ResponseEntity<List<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		/**
		 * This method used to delete the test with particular testId
		 * @return String that Delete operation is successful else throws an
		 *         IdNotFoundException
		 */
		@DeleteMapping("/deleteTest/{testId}")  
		public ResponseEntity<String> deleteTest(@PathVariable("testId") BigInteger testId) {
			
			String message = testservice.deleteTest(testId);  //Invoking a method - deleteTest
			//Condition - Checking whether the obtained object is null
			if (message == null) {
				throw new IdNotFoundException("Delete operation is unsuccessful");  
				
			
			} else {
				//returning the ResponseEntity<String> with httpStatus and httpHeaders
				return new ResponseEntity<String>("Delete operation is successful", new HttpHeaders(), HttpStatus.OK);
			}
		}
		/**
		 * This method used to update the test with particular testId
		 * @return String that Test updated successfully or else throws an
		 *         IdNotFoundException
		 */
		@PutMapping("/updateTest/{testId}")
		public ResponseEntity<String> updateTest(@PathVariable("testId") BigInteger testId,@RequestBody Test test) {
			Test testDetails = testservice.updateTest(testId,test);
			//Condition - Checking whether the obtained object is null
			if (testDetails == null) {
				
				throw new IdNotFoundException("Update Operation Unsuccessful,Provided testId does not exist");
			
			} else {
				return new ResponseEntity<String>("Test updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		//Exception Handling
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<String> userNotFound(IdNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
