package com.cg.anurag.b2.imsds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.cg.anurag.b2.imsds.dto.DisplaySupplier;
import com.cg.anurag.b2.imsds.service.DisplaySupplierService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DisplaySupplierDetailsApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort
	int serverPort;
	@Autowired
	DisplaySupplierService displaySupplierService;
	@Test
	public void getSupplierDetail_Positive() throws Exception
	{
		String url="http://localhost:"+serverPort+"GetSupplierDetail/12345";
		ResponseEntity<DisplaySupplier> displaySupplier = testRestTemplate.getForEntity(url,DisplaySupplier.class);
		Assertions.assertEquals(200, displaySupplier.getStatusCodeValue());
	}
	@Test
	public void getSupplierDetail_Negative() throws Exception
	{
		String url="http://localhost:"+serverPort+"GetSupplierDetail/12345678";
		ResponseEntity<String> message = testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(404, message.getStatusCodeValue());
	}
	@Test
	public void getDetails_Positive() throws Exception
	{   String supplierId="12345";
		DisplaySupplier displaySupplier =  displaySupplierService.getSupplierDetails(supplierId);
		boolean value;
		if(displaySupplier!=null)
		{
			value = true;
		}
		else {
			 value = false;
		}
		Assertions.assertEquals(true,value);
	}
	
}
