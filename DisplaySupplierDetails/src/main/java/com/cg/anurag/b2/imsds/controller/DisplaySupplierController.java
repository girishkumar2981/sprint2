package com.cg.anurag.b2.imsds.controller;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.anurag.b2.imsds.dto.DisplaySupplier;
import com.cg.anurag.b2.imsds.service.DisplaySupplierService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DisplaySupplierController {
@Autowired
DisplaySupplierService displaySupplierService;
@Autowired
RestTemplate rest;

public void setDisplaySupplierService(DisplaySupplierService displaySupplierService) {
	this.displaySupplierService = displaySupplierService;
}

@GetMapping("/GetSupplierDetail/{supplierId}")
private ResponseEntity<DisplaySupplier> getSupplierDetail(@PathVariable String supplierId) {
	try
	{
	DisplaySupplier displaySupplier = displaySupplierService.getSupplierDetails(supplierId);
	if (displaySupplier!=null) {
		return new ResponseEntity<DisplaySupplier>(displaySupplier, new HttpHeaders(), HttpStatus.OK);
	} else {
		throw new NoSuchElementException();
	}
	}
	catch(NoSuchElementException e)
	{
		return new ResponseEntity("SupplierId does not exist,so we couldn't fetch details",new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	}
}


