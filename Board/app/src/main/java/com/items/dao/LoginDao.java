package com.items.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.items.domain.Member;

@Mapper
public interface LoginDao {

	Member loginUserAuth(Map<String, String> map); // login ������ ��ġ�ϸ� User ������ ����
}