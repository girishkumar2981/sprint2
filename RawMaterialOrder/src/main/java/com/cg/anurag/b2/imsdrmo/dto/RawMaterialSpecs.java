package com.cg.anurag.b2.imsdrmo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rawmaterialspecifications")
public class RawMaterialSpecs {
@Id
@Column(name="rawmaterialid")
private String rawmaterialId;
@Column(name="rawmaterialname")
private String rawmaterialname;
@Column(name="priceperunit")
private double priceperunit;
@Column(name="manufacturingdate")
private Date manufacturingdate;
@Column(name="expirydate")
private Date expirydate;
@Column(name="supplierid")
private String supplierId;
@Column(name="warehouseid")
private String warehouseId;
public RawMaterialSpecs() {}
public RawMaterialSpecs(String rawmaterialId, String rawmaterialname, double priceperunit, Date manufacturingdate,
		Date expirydate, String supplierId, String warehouseId) {
	this.rawmaterialId = rawmaterialId;
	this.rawmaterialname = rawmaterialname;
	this.priceperunit = priceperunit;
	this.manufacturingdate = manufacturingdate;
	this.expirydate = expirydate;
	this.supplierId = supplierId;
	this.warehouseId = warehouseId;
}
public String getRawmaterialId() {
	return rawmaterialId;
}
public void setRawmaterialId(String rawmaterialId) {
	this.rawmaterialId = rawmaterialId;
}
public String getRawmaterialname() {
	return rawmaterialname;
}
public void setRawmaterialname(String rawmaterialname) {
	this.rawmaterialname = rawmaterialname;
}
public double getPriceperunit() {
	return priceperunit;
}
public void setPriceperunit(double priceperunit) {
	this.priceperunit = priceperunit;
}
public Date getManufacturingdate() {
	return manufacturingdate;
}
public void setManufacturingdate(Date manufacturingdate) {
	this.manufacturingdate = manufacturingdate;
}
public Date getExpirydate() {
	return expirydate;
}
public void setExpirydate(Date expirydate) {
	this.expirydate = expirydate;
}
public String getSupplierId() {
	return supplierId;
}
public void setSupplierId(String supplierId) {
	this.supplierId = supplierId;
}
public String getWarehouseId() {
	return warehouseId;
}
public void setWarehouseId(String warehouseId) {
	this.warehouseId = warehouseId;
}


	}


