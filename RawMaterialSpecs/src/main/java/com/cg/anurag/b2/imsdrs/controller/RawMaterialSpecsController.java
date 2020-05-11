package com.cg.anurag.b2.imsdrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.anurag.b2.imsdrs.dto.RawMaterialSpecs;
import com.cg.anurag.b2.imsdrs.service.RawMaterialSpecsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RawMaterialSpecsController {
@Autowired
RawMaterialSpecsService rmss;
@Autowired
RestTemplate rest;
@GetMapping("/GetAllRawMaterialSpecs")
private ResponseEntity<List<RawMaterialSpecs>> getAllSpecs() 
    {
	List<RawMaterialSpecs> specslist = rmss.getAllRawMaterialSpecs();
	return new ResponseEntity<List<RawMaterialSpecs>>(specslist, new HttpHeaders(), HttpStatus.OK);
    }
	
}
