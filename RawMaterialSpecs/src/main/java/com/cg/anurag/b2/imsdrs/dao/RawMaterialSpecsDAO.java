package com.cg.anurag.b2.imsdrs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.anurag.b2.imsdrs.dto.RawMaterialSpecs;

@Repository
public interface RawMaterialSpecsDAO extends JpaRepository<RawMaterialSpecs,String>
{
	 


}
