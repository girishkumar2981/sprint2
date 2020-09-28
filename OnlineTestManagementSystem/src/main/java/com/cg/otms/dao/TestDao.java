package com.cg.otms.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.otms.dto.Test;
/**
 * JpaRepository contains methods for performing CRUD operations
 */
@Repository                
public interface TestDao extends JpaRepository<Test,BigInteger>{
	
}