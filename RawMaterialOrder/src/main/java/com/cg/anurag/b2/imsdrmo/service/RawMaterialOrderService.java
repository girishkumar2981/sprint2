package com.cg.anurag.b2.imsdrmo.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.anurag.b2.imsdrmo.dao.RawMaterialOrderDAO;
import com.cg.anurag.b2.imsdrmo.dto.Orders;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;

@Service
public class RawMaterialOrderService {
@Autowired
RawMaterialOrderDAO rmo;
public void setRmo(RawMaterialOrderDAO rmo) {
	this.rmo = rmo;
}
@Transactional
public RawMaterialOrder placeorder(RawMaterialOrder prmo,RawMaterialSpecs rawmaterialspes,double quantityvalue ) {
	
	double unitprice=rawmaterialspes.getPriceperunit();
	prmo.setTotalprice(unitprice*quantityvalue);
	prmo.setRawmaterialname(rawmaterialspes.getRawmaterialname());
	prmo.setPriceperunit(rawmaterialspes.getPriceperunit());
	prmo.setQuantityvalue(quantityvalue);
	prmo.setWarehouseId(rawmaterialspes.getWarehouseId());
	prmo.setSupplierId(rawmaterialspes.getSupplierId());
	prmo.setManufacturingdate(rawmaterialspes.getManufacturingdate());
	prmo.setExpirydate(rawmaterialspes.getExpirydate());
	prmo.setDeliverystatus("processing");
	LocalDate doo = LocalDate.now();
	prmo.setDateoforder(LocalDate.now());
	LocalDate delivery = doo.plusDays(5);
	prmo.setDateofdelivery(delivery);;
	prmo.setRawmaterialId(rawmaterialspes.getRawmaterialId());
return rmo.save(prmo);
}
@Transactional
public  RawMaterialOrder trackrawmaterialorder (int orderId)
{
	return rmo.findById(orderId).get();
}
@Transactional
public Orders getRawMaterialOrder(String supplierId,String deliverystatus,LocalDate sd,LocalDate ed)
{
	List<RawMaterialOrder> list = rmo.findAllOrdersBySupplierId(supplierId);
	List<RawMaterialOrder> slist=new ArrayList<>();
	for(RawMaterialOrder r : list)
	{
	if(r.getDateoforder().isAfter(sd)&& r.getDateoforder().isBefore(ed)&&r.getDeliverystatus().equalsIgnoreCase(deliverystatus))
	{
		slist.add(r);
		
	}
	}
	Orders o=new Orders();
	o.setOrders(slist);
	return o;
	
}
@Transactional
public boolean updaterawmaterialorder(int orderId,String deliverystatus)
{
	RawMaterialOrder v=rmo.findById(orderId).get();
	if(v!=null)
	{
		v.setDeliverystatus(deliverystatus);
		return true;
	}
	return false;
}

}

