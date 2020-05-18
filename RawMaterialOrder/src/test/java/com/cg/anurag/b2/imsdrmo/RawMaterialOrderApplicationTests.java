package com.cg.anurag.b2.imsdrmo;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.cg.anurag.b2.imsdrmo.dto.Orders;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RawMaterialOrderApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort
	int serverPort;
	@Test
	public void trackOrder_Positive() throws Exception
	{
		String url = "http://localhost:"+serverPort+"/trackorder/1256";
		ResponseEntity<RawMaterialOrder> rawMaterialOrder = testRestTemplate.getForEntity(url,RawMaterialOrder.class);
		Assertions.assertEquals(200, rawMaterialOrder.getStatusCodeValue());
	}
	@Test
	public void trackOrder_Negative() throws Exception
	{
		String url = "http://localhost:"+serverPort+"/trackorder/1879";
		ResponseEntity<String> message = testRestTemplate.getForEntity(url, String.class);
		Assertions.assertEquals(404, message.getStatusCodeValue());
	}
	@Test
	public void displayOrder_Positive() throws Exception
	{
		String url = "http://localhost:"+serverPort+"/getrawmaterialorder/supplierid/leatherstore11/deliverystatus/processing/startDate/2020-05-10/endDate/2020-05-19";
		ResponseEntity<Orders> orders = testRestTemplate.getForEntity(url,Orders.class);
		Assertions.assertEquals(200, orders.getStatusCodeValue());
	}
	@Test
	public void displayOrder_Negative() throws Exception
	{
		String url = "http://localhost:"+serverPort+"/getrawmaterialorder/supplierid/leatherstore1100/deliverystatus/processing/startDate/2020-05-10/endDate/2020-05-19";
		ResponseEntity<String> message = testRestTemplate.getForEntity(url, String.class);
		Assertions.assertEquals(404, message.getStatusCodeValue());
	}

@Test
public void updateDeliveryStatus_Positive() throws Exception
{
	String url = "http://localhost:"+serverPort+"Updatedeliverystatus/1256/delivered";
    ResponseEntity<String> result = testRestTemplate.exchange(url, HttpMethod.PUT, null, String.class);
    Assertions.assertEquals(200, result.getStatusCodeValue());
}
@Test
public void updateDeliveryStatus_Negative() throws Exception
{
	String url = "http://localhost:"+serverPort+"Updatedeliverystatus/1787/delivered";
    ResponseEntity<String> result = testRestTemplate.exchange(url, HttpMethod.PUT, null, String.class);
    Assertions.assertEquals(404, result.getStatusCodeValue());
}
/*@Test
public void placeOrder_Positive() throws Exception
{  
	int unitprice = 100;
	double quantityvalue = 10;
	String url = "http://localhost:"+serverPort+"/placeorder/5";
	RawMaterialOrder rawMaterialOrder = new RawMaterialOrder();
	rawMaterialOrder.setTotalprice(unitprice*quantityvalue);
	rawMaterialOrder.setRawmaterialname("fabric");
	rawMaterialOrder.setPriceperunit(unitprice);
	rawMaterialOrder.setQuantityvalue(quantityvalue);
	rawMaterialOrder.setWarehouseId("handloom india");
	rawMaterialOrder.setSupplierId("fabric india");
	rawMaterialOrder.setManufacturingdate(01-FEB-19 );
	rawMaterialOrder.setExpirydate(2030-03-01);
	rawMaterialOrder.setDeliverystatus("processing");
	LocalDate localDate = LocalDate.now();
	rawMaterialOrder.setDateoforder(LocalDate.now());
	LocalDate deliveryDate = localDate.plusDays(5);
	rawMaterialOrder.setDateofdelivery(deliveryDate);;
	rawMaterialOrder.setRawmaterialId("fabric 101");
}*/
}
