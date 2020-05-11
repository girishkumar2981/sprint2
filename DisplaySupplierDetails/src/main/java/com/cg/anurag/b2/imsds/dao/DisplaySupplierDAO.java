package com.cg.anurag.b2.imsds.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.anurag.b2.imsds.dto.DisplaySupplier;

@Repository
public interface DisplaySupplierDAO extends JpaRepository<DisplaySupplier,String> {

}
