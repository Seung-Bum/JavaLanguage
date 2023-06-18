package com.items.service;

import java.util.Map;

import com.items.domain.Member;

public interface LoginService {
	public Member loginUserAuth(Map<String, String> map);
}
