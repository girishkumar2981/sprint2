package com.cg.anurag.b2.imsdrmo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;

public interface RawMaterialOrderDAO extends JpaRepository<RawMaterialOrder,Integer> {
	public List<RawMaterialOrder> findAllOrdersBySupplierId(String supplierId);
}
