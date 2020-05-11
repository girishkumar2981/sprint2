package com.cg.anurag.b2.imsds.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.anurag.b2.imsds.dto.DisplaySupplier;
import com.cg.anurag.b2.imsds.exception.IdNotFoundException;
import com.cg.anurag.b2.imsds.service.DisplaySupplierService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DisplaySupplierController {
@Autowired
DisplaySupplierService dss;
@Autowired
RestTemplate rest;
public void setDss(DisplaySupplierService dss) {
	this.dss = dss;
}
@GetMapping("/GetSupplierDetail/{supplierId}")
private ResponseEntity<DisplaySupplier> getSupplierDetail(@PathVariable String supplierId) {
	try
	{
	DisplaySupplier d = dss.getSupplierDetails(supplierId);
	if (d!=null) {
		return new ResponseEntity<DisplaySupplier>(d, new HttpHeaders(), HttpStatus.OK);
	} else {
		throw new NoSuchElementException();
	}
	}
	catch(NoSuchElementException e)
	{
		return new ResponseEntity("SupplierId does not exist,so we couldn't fetch details",new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	}
@DeleteMapping("/DeleteSupplier/{supplierId}")
private ResponseEntity<String> deleteSupplier(@PathVariable String supplierId)
	{
		DisplaySupplier e = dss.deleteSupplierDetails(supplierId);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Supplier deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
@PutMapping("/UpdateSupplier")
	public ResponseEntity<String> updateSupplier(@RequestBody DisplaySupplier s)
		{
			boolean e = dss.updateSupplierDetails(s);
			if (e == false) {
				throw new IdNotFoundException("Update details Unsuccessful,Provided Id does not exist");
			} else {
				return new ResponseEntity<String>("Supplier data updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
@GetMapping("/GetAllSuppliers")
private ResponseEntity<List<DisplaySupplier>> getAllSuppliers() 
    {
	List<DisplaySupplier> supplierlist = dss.getAllSuppliers();
	return new ResponseEntity<List<DisplaySupplier>>(supplierlist, new HttpHeaders(), HttpStatus.OK);
    }
@PostMapping("/addSupplier")
public ResponseEntity<String>addSupplier(@RequestBody DisplaySupplier s )
{
	DisplaySupplier e = dss.addSupplierDetails(s);
	if(e == null)
	{
		throw new IdNotFoundException("Enter Valid Id");
	}
	else {
		return new ResponseEntity<String>("Supplier Details added successfully",new HttpHeaders(),HttpStatus.OK);		
	}
}
}


