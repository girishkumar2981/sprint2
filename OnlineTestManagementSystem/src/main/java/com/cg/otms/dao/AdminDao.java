package com.cg.otms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.otms.dto.Admin;
/**
 * JpaRepository contains methods for performing CRUD operations
 */

@Repository                  
public interface AdminDao extends JpaRepository<Admin,String>{
	@Query("select adminId,adminContact,adminName,adminPassword,dateOfBirth from Admin where adminId=?1 and adminPassword=?2")
	Optional<Admin> adminLogin(String adminId,String adminPassword);
	
}