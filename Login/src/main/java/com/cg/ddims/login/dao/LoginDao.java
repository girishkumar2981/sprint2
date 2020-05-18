package com.cg.ddims.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ddims.login.dto.Login;
@Repository
public interface LoginDao extends JpaRepository<Login,Long>{

	
	
}
