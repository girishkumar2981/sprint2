package com.cg.anurag.b2.imsdrmo.dto;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="placerawmaterialorder")
public class RawMaterialOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="order_seq")
	@SequenceGenerator(sequenceName = "order_seq",allocationSize = 1,name = "order_seq")
	@Column(name="orderid")
	private int orderId;
	@Column(name="rawmaterialname")
	private String rawmaterialname;
	@Column(name="priceperunit")
	private double priceperunit;
	@Column(name="quantityvalue")
	private double quantityvalue;
	@Column(name="totalprice")
	private double totalprice;
	@Column(name="warehouseid")
	private String warehouseId;
	@Column(name="supplierid")
	private String supplierId;
	@Column(name="dateoforder")
	private LocalDate dateoforder;
	@Column(name="dateofdelivery")
	private LocalDate dateofdelivery;
	@Column(name="manufacturingdate")
	private Date manufacturingdate;
	@Column(name="expirydate")
	private Date expirydate;
	@Column(name="deliverystatus")
	private String deliverystatus;
	@Column(name="rawmaterialid")
	private String rawmaterialId;
	public RawMaterialOrder() {}
	public RawMaterialOrder(int orderId, String rawmaterialname, double priceperunit, double quantityvalue,
			double totalprice, String warehouseId, String supplierId, LocalDate dateoforder, LocalDate dateofdelivery,
			Date manufacturingdate, Date expirydate, String deliverystatus, String rawmaterialId) {
		this.orderId = orderId;
		this.rawmaterialname = rawmaterialname;
		this.priceperunit = priceperunit;
		this.quantityvalue = quantityvalue;
		this.totalprice = totalprice;
		this.warehouseId = warehouseId;
		this.supplierId = supplierId;
		this.dateoforder = dateoforder;
		this.dateofdelivery = dateofdelivery;
		this.manufacturingdate = manufacturingdate;
		this.expirydate = expirydate;
		this.deliverystatus = deliverystatus;
		this.rawmaterialId = rawmaterialId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public double getQuantityvalue() {
		return quantityvalue;
	}
	public void setQuantityvalue(double quantityvalue) {
		this.quantityvalue = quantityvalue;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public LocalDate getDateoforder() {
		return dateoforder;
	}
	public void setDateoforder(LocalDate dateoforder) {
		this.dateoforder = dateoforder;
	}
	public LocalDate getDateofdelivery() {
		return dateofdelivery;
	}
	public void setDateofdelivery(LocalDate dateofdelivery) {
		this.dateofdelivery = dateofdelivery;
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
	public String getDeliverystatus() {
		return deliverystatus;
	}
	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}
	public String getRawmaterialId() {
		return rawmaterialId;
	}
	public void setRawmaterialId(String rawmaterialId) {
		this.rawmaterialId = rawmaterialId;
	}
}
	