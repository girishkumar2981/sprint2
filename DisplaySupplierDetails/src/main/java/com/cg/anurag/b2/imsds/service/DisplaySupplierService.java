package com.cg.anurag.b2.imsds.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.anurag.b2.imsds.dao.DisplaySupplierDAO;
import com.cg.anurag.b2.imsds.dto.DisplaySupplier;

@Service
public class DisplaySupplierService {
@Autowired
DisplaySupplierDAO displaySupplierDao;

public void setDisplaySupplierDao(DisplaySupplierDAO displaySupplierDao) {
	this.displaySupplierDao = displaySupplierDao;
}

@Transactional
public DisplaySupplier getSupplierDetails(String supplierId)
{
	return displaySupplierDao.findById(supplierId).get();
}

}
