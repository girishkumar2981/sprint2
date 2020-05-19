package com.cg.anurag.b2.imsdrmo;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;
import com.cg.anurag.b2.imsdrmo.service.RawMaterialOrderService;
@SpringBootTest
public class RawMaterialOrderServiceApplicationTests {
	@Autowired
	RawMaterialOrderService rawMaterialOrderService;
	@Test
	public void trackingOrder() throws Exception
	{
		RawMaterialOrder rawMaterialOrder = rawMaterialOrderService.trackRawmaterialOrder(1256);
		Assertions.assertNotNull(rawMaterialOrder);
	}
	@Test
	public void displayRawMaterialOrder_Positive() throws Exception
	{
		String supplierId = "leatherstore11";
		List<RawMaterialOrder> rawMaterialOrder = rawMaterialOrderService.getRawMaterialOrder(supplierId);
		boolean value;
		if(rawMaterialOrder.isEmpty())
		{
			value=false;
			
		}
		else
		{
			value=true;
		}
		Assertions.assertEquals(true,value);
	}
	@Test
	public void displayRawMaterialOrder_Negative() throws Exception
	{
		String supplierId = "leatherstore17";
		List<RawMaterialOrder> rawMaterialOrder = rawMaterialOrderService.getRawMaterialOrder(supplierId);
		boolean value;
		if(rawMaterialOrder.isEmpty())
		{
			value=false;
			
		}
		else
		{
			value=true;
		}
		Assertions.assertEquals(false,value);
	}
	@Test
	public void updateDetails_Positive() throws Exception
	{
		int orderId = 1256;
		String deliverystatus = "processing";
		boolean value = rawMaterialOrderService.updateRawmaterialOrder(orderId,deliverystatus);
		Assertions.assertEquals(true,value);
	}
	@Test
	public void updateDetails_Negative() throws Exception
	{
		int orderId = 1089;
		String deliverystatus = "delayed";
		boolean value = rawMaterialOrderService.updateRawmaterialOrder(orderId,deliverystatus);
		Assertions.assertEquals(false,value);
	}
	@Test
	public void placeOrder() throws Exception
	
	{   double quantityvalue=5;
		RawMaterialOrder rawMaterialOrder = new RawMaterialOrder ();
		RawMaterialSpecs rawMaterialSpecs = new RawMaterialSpecs();
		rawMaterialSpecs.setRawmaterialId("fabric101");
		rawMaterialSpecs.setRawmaterialname("fabric");
		rawMaterialSpecs.setPriceperunit(1000);
		rawMaterialSpecs.setWarehouseId("indianleather");
		rawMaterialSpecs.setSupplierId("leatherstore");
		rawMaterialOrder.setTotalprice(1000*quantityvalue);
		rawMaterialOrder.setRawmaterialname(rawMaterialSpecs.getRawmaterialname());
		rawMaterialOrder.setPriceperunit(rawMaterialSpecs.getPriceperunit());
		rawMaterialOrder.setQuantityvalue(quantityvalue);
		rawMaterialOrder.setWarehouseId(rawMaterialSpecs.getWarehouseId());
		rawMaterialOrder.setSupplierId(rawMaterialSpecs.getSupplierId());
		LocalDate localDate = LocalDate.now();
		rawMaterialOrder.setManufacturingdate(null);
		rawMaterialOrder.setExpirydate(null);
		rawMaterialOrder.setDeliverystatus("processing");
		rawMaterialOrder.setDateoforder(LocalDate.now());
		rawMaterialOrder.setDateofdelivery(localDate.plusDays(5));
		rawMaterialOrder.setRawmaterialId(rawMaterialSpecs.getRawmaterialId());
		RawMaterialOrder rawmaterialOrder = rawMaterialOrderService.placeOrder(rawMaterialOrder, rawMaterialSpecs, quantityvalue);
		Assertions.assertNotNull(rawmaterialOrder);
	}
}
