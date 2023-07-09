package com.items.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestAPIDao {
	void insertAirInfo(HashMap<String, String> param);
}