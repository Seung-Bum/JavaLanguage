package com.newlecture.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Promgram {

	public static void main(String[] args) {
//		GList<Integer> list = new GList<>(); 공부 목적 구현
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(7);
		list.add(7);
		System.out.println(list.get(5)); // 7
		System.out.println(list.size()); // 6
	
		
		Set<Integer> set = new HashSet<>();
		set.add(3);
		set.add(5);
		set.add(6);
		set.add(7);
		set.add(7);
		set.add(7);
		System.out.println(set.size()); // 4
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", 3);
		map.put("title", "Hello");
		map.put("hit", 12);
		
		System.out.println(map.get("title"));
		
		/*
		int size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.clear();
		size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.add(7);
		int num = list.get(0); // Object => Integer
		// 참조를 다른 형식으로 바꿔주기만 하면됨
		// 꺼낼때 너는 무슨 형이니 라고 물어봐야함
		// 범용자료 형식의 문제점
		System.out.printf("num : %d\n", num);
		*/
	}

}
