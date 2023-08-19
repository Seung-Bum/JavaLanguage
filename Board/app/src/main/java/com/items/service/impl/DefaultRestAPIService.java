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
	public void insertAirInfo(HashMap<String, Object> param) {
		restAPIDao.insertAirInfo(param);
	}
	
	@Override
	public void insertDepartureData(HashMap<String, Object> param) {
		restAPIDao.insertDepartureData(param);
	}

	@Override
	public HashMap<String, Object> departureDataSeq() {
		return restAPIDao.departureDataSeq();		
	}
	
//	@Override
//	public HashMap<String, Object> selectDepartureData(HashMap<String, Object> param) {
//		return restAPIDao.selectDepartureData(param);
//	}
}
