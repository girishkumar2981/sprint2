package com.cg.anurag.b2.imsds.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.anurag.b2.imsds.dao.DisplaySupplierDAO;
import com.cg.anurag.b2.imsds.dto.DisplaySupplier;

@Service
public class DisplaySupplierService {
@Autowired
DisplaySupplierDAO sdao;
public void setSdao(DisplaySupplierDAO sdao) {
	this.sdao = sdao;
}
@Transactional
public DisplaySupplier getSupplierDetails(String supplierId)
{
	return sdao.findById(supplierId).get();
}
@Transactional
public DisplaySupplier deleteSupplierDetails(String supplierId)
{
	DisplaySupplier ds=sdao.findById(supplierId).get();
	if(ds!=null)
	{
		sdao.deleteById(supplierId);
	}
	return ds;
}
@Transactional
public List<DisplaySupplier> getAllSuppliers() {
	return sdao.findAll();
	
}
@Transactional
public DisplaySupplier addSupplierDetails(DisplaySupplier s)
{
	return sdao.save(s);
}
@Transactional
public boolean updateSupplierDetails(DisplaySupplier s)
{
	DisplaySupplier dss=sdao.findById(s.getSupplierId()).get();
	if(dss!=null)
	{
		dss.setSupplierId(s.getSupplierId());
		dss.setName(s.getName());
		dss.setAddress(s.getAddress());
		dss.setPhoneno(s.getPhoneno());
		return true;
	}
	return false;
}
}
