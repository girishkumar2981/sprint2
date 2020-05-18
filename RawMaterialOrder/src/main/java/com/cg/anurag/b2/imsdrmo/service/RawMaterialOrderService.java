package com.cg.anurag.b2.imsdrmo.service;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.anurag.b2.imsdrmo.dao.RawMaterialOrderDAO;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;

@Service
public class RawMaterialOrderService {
@Autowired
RawMaterialOrderDAO rawMaterialOrderDao;

public void setRawMaterialOrderDao(RawMaterialOrderDAO rawMaterialOrderDao) {
	this.rawMaterialOrderDao = rawMaterialOrderDao;
}
@Transactional
public RawMaterialOrder placeOrder(RawMaterialOrder rawMaterialOrder,RawMaterialSpecs rawMaterialSpecs,double quantityvalue ) {
	
	double unitprice=rawMaterialSpecs.getPriceperunit();
	rawMaterialOrder.setTotalprice(unitprice*quantityvalue);
	rawMaterialOrder.setRawmaterialname(rawMaterialSpecs.getRawmaterialname());
	rawMaterialOrder.setPriceperunit(rawMaterialSpecs.getPriceperunit());
	rawMaterialOrder.setQuantityvalue(quantityvalue);
	rawMaterialOrder.setWarehouseId(rawMaterialSpecs.getWarehouseId());
	rawMaterialOrder.setSupplierId(rawMaterialSpecs.getSupplierId());
	rawMaterialOrder.setManufacturingdate(rawMaterialSpecs.getManufacturingdate());
	rawMaterialOrder.setExpirydate(rawMaterialSpecs.getExpirydate());
	rawMaterialOrder.setDeliverystatus("processing");
	LocalDate localDate = LocalDate.now();
	rawMaterialOrder.setDateoforder(LocalDate.now());
	LocalDate deliveryDate = localDate.plusDays(5);
	rawMaterialOrder.setDateofdelivery(deliveryDate);;
	rawMaterialOrder.setRawmaterialId(rawMaterialSpecs.getRawmaterialId());
return rawMaterialOrderDao.save(rawMaterialOrder);
}
@Transactional
public  RawMaterialOrder trackRawmaterialOrder (int orderId)
{
	return rawMaterialOrderDao.findById(orderId).get();
}
@Transactional
public List<RawMaterialOrder> getRawMaterialOrder(String supplierId)
{
	return rawMaterialOrderDao.findAllOrdersBySupplierId(supplierId);
}
	
@Transactional
public boolean updateRawmaterialOrder(int orderId,String deliverystatus)
{
	try {
	RawMaterialOrder rawMaterialOrder=rawMaterialOrderDao.findById(orderId).get();
	if(rawMaterialOrder!=null)
	{
		rawMaterialOrder.setDeliverystatus(deliverystatus);
		return true;
	}
	}
	catch(Exception e)
	{
	return false;
}
return false;
}
}
