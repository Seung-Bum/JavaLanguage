package com.items.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.items.dao.LoginDao;
import com.items.service.LoginService;

@Service
public class DefaultLoginService implements LoginService {
	
	
	@Autowired(required=true)
	LoginDao loginDao;
	
	@Override
	public List<Map<String,Object>> list() {
		List<Map<String, Object>> boardList = boardDao.findAll();
		return boardList;
	}
	
	@Override
	public void loginUserAuth(Map<String, String> map) {		
		
	}
	
}
