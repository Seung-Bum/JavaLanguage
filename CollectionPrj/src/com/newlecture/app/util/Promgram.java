package com.newlecture.app.util;

public class Promgram {

	public static void main(String[] args) {
		ObjectList list = new ObjectList();
		//list.add("hello"); // int가 아니라 문자담김
		list.add(3);
		list.add(5);
		int size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.clear();
		size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.add(7);
		int num = (Integer)list.get(0); // Object => Integer
		// 참조를 다른 형식으로 바꿔주기만 하면됨
		// 꺼낼때 너는 무슨 형이니 라고 물어봐야함
		// 범용자료 형식의 문제점
		System.out.printf("num : %d\n", num);
		
	}

}
