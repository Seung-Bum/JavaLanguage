package com.items.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.items.dao.LoginDao;
import com.items.domain.Member;
import com.items.service.LoginService;

@Service
public class DefaultLoginService implements LoginService {	
	
	@Autowired(required=true)
	LoginDao loginDao;
		
	@Override
	public Member loginUserAuth(Map<String, String> map) {		
		Member LoginUser = loginDao.loginUserAuth(map);
		return LoginUser;
	}
	
}
