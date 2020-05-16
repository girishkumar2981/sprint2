
package com.cg.anurag.b2.imsdrs.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.anurag.b2.imsdrs.dao.RawMaterialSpecsDAO;
import com.cg.anurag.b2.imsdrs.dto.RawMaterialSpecs;

@Service
public class RawMaterialSpecsService {
	@Autowired
	RawMaterialSpecsDAO rawMaterialSpecsDao;
	
	public RawMaterialSpecsDAO getRawMaterialSpecsDao() {
		return rawMaterialSpecsDao;
	}

	public void setRawMaterialSpecsDao(RawMaterialSpecsDAO rawMaterialSpecsDao) {
		this.rawMaterialSpecsDao = rawMaterialSpecsDao;
	}

	@Transactional
	public List<RawMaterialSpecs> getAllRawMaterialSpecs() {
		return rawMaterialSpecsDao.findAll();
		
	}

}
