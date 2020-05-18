
package com.cg.anurag.b2.imsdrmo.controller;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cg.anurag.b2.imsdrmo.service.RawMaterialOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RawMaterialOrderController {
@Autowired
RawMaterialOrderService rawMaterialOrderService;
public void setRawMaterialOrderService(RawMaterialOrderService rawMaterialOrderService) {
	this.rawMaterialOrderService = rawMaterialOrderService;
}
@Autowired
RestTemplate rest;
@GetMapping(value="/getrawmaterialorder/supplierid/{supplierId}/deliverystatus/{deliverystatus}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
public ResponseEntity<Orders> getRawMaterialOrder(@PathVariable String supplierId,@PathVariable String deliverystatus,@PathVariable String startDate,@PathVariable String endDate)throws ParseException
{
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate startedDate = LocalDate.parse(startDate, formatter);
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate endedDate=LocalDate.parse(endDate,formatter1);
	try {
		
		    List<RawMaterialOrder> list = rawMaterialOrderService.getRawMaterialOrder(supplierId);
		    if(list.isEmpty())
		    {
		    	return new ResponseEntity("supplierId doesnot exists",HttpStatus.NOT_FOUND);
		    }
		    else
		    {
			List<RawMaterialOrder> slist=new ArrayList<>();
			for(RawMaterialOrder rawMaterialOrder : list)
			{
			if(rawMaterialOrder.getDateoforder().isAfter(startedDate)&& rawMaterialOrder.getDateoforder().isBefore(endedDate)&&rawMaterialOrder.getDeliverystatus().equalsIgnoreCase(deliverystatus))
			{
				slist.add(rawMaterialOrder);
				
			}
			
			}
			Orders orders=new Orders();
			orders.setOrders(slist);
			return  new ResponseEntity<Orders>(orders,new HttpHeaders(),HttpStatus.OK);
	}
	}
	
		catch(Exception e)
		{
			return new ResponseEntity("supplierId doesnot exists",new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
	}

@PostMapping("/placeorder/{quantityvalue}")
public ResponseEntity<String> placeAnOrder(@RequestBody RawMaterialSpecs rawMaterialSpecs,@PathVariable double quantityvalue)
{
	try
	{
	RawMaterialOrder rawMaterialOrder=new RawMaterialOrder();
	RawMaterialOrder rawMaterialOrderr =rawMaterialOrderService.placeOrder(rawMaterialOrder,rawMaterialSpecs,quantityvalue);
	if(rawMaterialOrderr!=null) {
		return new ResponseEntity<>("order placed successfully", new HttpHeaders(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>("cannot place order", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	}
	catch(Exception e)
	{
		return new ResponseEntity<>("cannot place order", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
@GetMapping("/trackorder/{orderId}")
public ResponseEntity<RawMaterialOrder> getOrder(@PathVariable int orderId) {
	try {
	RawMaterialOrder rawMaterialOrder = rawMaterialOrderService.trackRawmaterialOrder(orderId);
	if (rawMaterialOrder!= null) {
		return new ResponseEntity<RawMaterialOrder>(rawMaterialOrder, new HttpHeaders(), HttpStatus.OK);
	} else {
		throw new NoSuchElementException();
	}
	}
	catch(NoSuchElementException e)
	{
		return new ResponseEntity("orderId doesnot exists", new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
}
@PutMapping("/Updatedeliverystatus/{orderId}/{deliverystatus}")
public ResponseEntity<String> updateOrder(@PathVariable int orderId,@PathVariable String deliverystatus)
	{
		try
		{
		boolean value = rawMaterialOrderService.updateRawmaterialOrder(orderId,deliverystatus);
		if (value==true) {
			return new ResponseEntity<String>("delivery status updated successfully", new HttpHeaders(), HttpStatus.OK);
		} else {
			throw new NoSuchElementException();
		}
		}
		catch(NoSuchElementException e)
		{
			return new ResponseEntity<String>("Update details Unsuccessful,Provided Id does not exist",HttpStatus.NOT_FOUND);
		}
	}
}