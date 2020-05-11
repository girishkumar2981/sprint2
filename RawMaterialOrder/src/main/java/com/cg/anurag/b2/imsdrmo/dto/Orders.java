package com.cg.anurag.b2.imsdrmo.dto;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement
public class Orders {
private List<RawMaterialOrder> orders;
public Orders( ) {}
public Orders(List<RawMaterialOrder> orders) {
	
	this.orders = orders;
}
public List<RawMaterialOrder> getOrders() {
	return orders;
}
public void setOrders(List<RawMaterialOrder> orders) {
	this.orders = orders;
}


}
