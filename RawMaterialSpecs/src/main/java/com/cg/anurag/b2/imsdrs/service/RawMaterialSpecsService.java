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
	RawMaterialSpecsDAO rmsd;
	public void setRmsd(RawMaterialSpecsDAO rmsd) {
		this.rmsd = rmsd;
	}
	@Transactional
	public List<RawMaterialSpecs> getAllRawMaterialSpecs() {
		return rmsd.findAll();
		
	}

}
