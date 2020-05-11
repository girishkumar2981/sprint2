package com.cg.anurag.b2.imsdrmo.controller;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cg.anurag.b2.imsdrmo.dto.Orders;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;
import com.cg.anurag.b2.imsdrmo.exception.IdNotFoundException;
import com.cg.anurag.b2.imsdrmo.exception.UnsuccessfullOrder;
import com.cg.anurag.b2.imsdrmo.service.RawMaterialOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RawMaterialOrderController {
@Autowired
RawMaterialOrderService rmos;
public void setRmos(RawMaterialOrderService rmos) {
	this.rmos = rmos;
}
@Autowired
RestTemplate rest;
@GetMapping(value="/getrawmaterialorder/supplierid/{supplierId}/deliverystatus/{deliverystatus}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
public ResponseEntity<Orders> getRawMaterialOrder(@PathVariable String supplierId,@PathVariable String deliverystatus,@PathVariable String startDate,@PathVariable String endDate)throws ParseException
{
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate sd = LocalDate.parse(startDate, formatter);
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate ed=LocalDate.parse(endDate,formatter1);
	Orders oo=rmos.getRawMaterialOrder(supplierId,deliverystatus,sd,ed);
	if(oo!=null)
	{
		return new ResponseEntity<Orders>(oo,HttpStatus.OK);
	}
	else
		return new ResponseEntity("Not successful",HttpStatus.NOT_FOUND);
}
@PostMapping("/placeorder/{quantityvalue}")
public ResponseEntity<RawMaterialOrder> placeorder(@RequestBody RawMaterialSpecs rawmaterialspes,@PathVariable double quantityvalue)
{
	RawMaterialOrder prmo=new RawMaterialOrder();
	RawMaterialOrder t =rmos.placeorder(prmo,rawmaterialspes,quantityvalue);
	if(t==null) {
		throw new IdNotFoundException("Cannot place order");
	} else {
		return new ResponseEntity<RawMaterialOrder>(t, new HttpHeaders(), HttpStatus.OK);
	}
	}

@GetMapping("/trackorder/{orderId}")
public ResponseEntity<RawMaterialOrder> getorder(@PathVariable int orderId) {
	try {
	RawMaterialOrder d = rmos.trackrawmaterialorder(orderId);
	if (d!= null) {
		return new ResponseEntity<RawMaterialOrder>(d, new HttpHeaders(), HttpStatus.OK);
	} else {
		throw new NoSuchElementException();
	}
	}
	catch(NoSuchElementException e)
	{
		return new ResponseEntity("orderId doesnot exists",new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
}
@PutMapping("/Updatedeliverystatus/{orderId}/{deliverystatus}")
public ResponseEntity<String> updateorder(@PathVariable int orderId,@PathVariable String deliverystatus)
	{
		try
		{
		boolean e = rmos.updaterawmaterialorder(orderId,deliverystatus);
		if (e==false) {
			return new ResponseEntity<String>("Update details Unsuccessful,Provided Id does not exist",HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("delivery status updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Update details Unsuccessful,Provided Id does not exist",HttpStatus.NOT_FOUND);
		}
	}
}