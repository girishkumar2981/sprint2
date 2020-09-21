package com.cg.otms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.otms.dao.UserDao;
import com.cg.otms.dto.Test;
import com.cg.otms.dto.User;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userdao;
	
	//User Login method
	public Optional<User> userLogin(String userId,String password)
	{
		
		return userdao.userLogin(userId, password);
		
	}

    //Retrieving test details of particular User
	public Test userTest(String userId) {
		User u=userdao.getOne(userId);
		return u.getUserTest();
	}
	
}

