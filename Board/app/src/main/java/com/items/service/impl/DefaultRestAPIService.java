package com.items.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.items.dao.RestAPIDao;
import com.items.service.RestAPIService;

@Service
public class DefaultRestAPIService implements RestAPIService {	
	
	@Autowired(required=true)
	RestAPIDao restAPIDao;

	@Override
	public void insertAirInfo(HashMap<String, String> param) {
		restAPIDao.insertAirInfo(param);
	}
}
