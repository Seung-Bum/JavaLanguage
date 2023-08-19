package com.items.service;

import java.util.HashMap;

import com.items.domain.Member;

public interface RestAPIService {
	public void insertAirInfo(HashMap<String, Object> param);

	public void insertDepartureData(HashMap<String, Object> param);
	
	public HashMap<String, Object> departureDataSeq();

	//public HashMap<String, Object> selectDepartureData(HashMap<String, Object> param);	
}
