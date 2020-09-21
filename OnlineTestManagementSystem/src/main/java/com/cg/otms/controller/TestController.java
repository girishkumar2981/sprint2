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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin("http://localhost:4200")
public class TestController {

	@Autowired
	
	TestService testservice;
	
	//Adding Test details into database
		@PostMapping("/addTest")
		public ResponseEntity<String> addTest(@RequestBody Test test) {
			Test testDetails = testservice.addTest(test);
			if (testDetails == null) {

				throw new IdNotFoundException("Test not added");

			} else {
				return new ResponseEntity<String>("Test added successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Retrieving test details with particular testId
		@GetMapping("/getTest/{testId}")
		public ResponseEntity<Optional<Test>> getTestById(@PathVariable("testId") BigInteger testId) {
			Optional<Test> testDetails = testservice.getTestById(testId);
			if (!testDetails.isPresent()) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
			} else {
				return new ResponseEntity<Optional<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Assigning test to particular user
		@GetMapping("/assignTest/{testId}/{userId}")
		public ResponseEntity<String> assignTest(@PathVariable("testId") BigInteger testId,@PathVariable("userId") String userId) {
			User userDetails = testservice.assignTest(testId,userId);
			if (userDetails == null) {
				throw new IdNotFoundException("Id does not exist,so we couldn't assign Test");
	          		
			} else {
				return new ResponseEntity<String>("Test assigned successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Retrieving question details with particular questionId
		@GetMapping("/getquestions/{testId}")
		public ResponseEntity<Set<Question>> getQuestionById(@PathVariable("testId") BigInteger testId) {
			Set<Question> questionDetails = testservice.getQuestionById(testId);
			if (questionDetails.isEmpty()) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
			} else {
				return new ResponseEntity<Set<Question>>(questionDetails, new HttpHeaders(), HttpStatus.OK);
			}
			
		}
		
		//Retrieving all the test details from the database
		@GetMapping("/testdetails")
		public ResponseEntity<List<Test>> TestDetails(){
			List<Test> testDetails=testservice.testDetails();
			
			if (testDetails.isEmpty()) {
				throw new IdNotFoundException("No Tests available");
			} else {
				return new ResponseEntity<List<Test>>(testDetails, new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		//Deleting Test with particular testId
		@DeleteMapping("/deleteTest/{testId}")
		public ResponseEntity<String> deleteTest(@PathVariable("testId") BigInteger testId) {
			String message = testservice.deleteTest(testId);
			if (message == null) {
				throw new IdNotFoundException("Delete operation is unsuccessful");
				
			
			} else {
				return new ResponseEntity<String>("Delete operation is successful", new HttpHeaders(), HttpStatus.OK);
			}
		}
}
