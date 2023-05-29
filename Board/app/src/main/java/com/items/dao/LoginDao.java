package com.items.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.items.domain.Member;

@Mapper
public interface LoginDao {

	Member loginUserAuth(Map<String, String> map); // login 정보가 일치하면 User 데이터 리턴
}