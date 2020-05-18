package com.cg.ddims.login.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ddims.login.dao.LoginDao;
import com.cg.ddims.login.dto.Login;

@Service
public class LoginService {
@Autowired
LoginDao loginDao;

public void setLoginDao(LoginDao loginDao) {
	this.loginDao = loginDao;
}

@Transactional
public Optional<Login> getLoginDetails(long userId)
{
	return loginDao.findById(userId);
}

}
