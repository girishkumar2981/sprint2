package com.cg.anurag.b2.imsds.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="displaysupplier")
public class DisplaySupplier {
@Id
@Column(name="supplierid")
String supplierId;
@Column(name="name")
String name;
@Column(name="address")
String address;
@Column(name="phoneno")
String phoneno;
public DisplaySupplier() {}
public DisplaySupplier(String supplierId, String name, String address, String phoneno) {
	this.supplierId = supplierId;
	this.name = name;
	this.address = address;
	this.phoneno = phoneno;
}
public String getSupplierId() {
	return supplierId;
}
public void setSupplierId(String supplierId) {
	this.supplierId = supplierId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhoneno() {
	return phoneno;
}
public void setPhoneno(String phoneno) {
	this.phoneno = phoneno;
}

}
