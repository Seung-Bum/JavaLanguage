package com.newlecture.app.util;

public class Promgram {

	public static void main(String[] args) {
		ObjectList list = new ObjectList();
		//list.add("hello"); // int�� �ƴ϶� ���ڴ��
		list.add(3);
		list.add(5);
		int size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.clear();
		size = list.size();
		System.out.printf("size : %d\n", size);
		
		list.add(7);
		int num = (Integer)list.get(0); // Object => Integer
		// ������ �ٸ� �������� �ٲ��ֱ⸸ �ϸ��
		// ������ �ʴ� ���� ���̴� ��� ���������
		// �����ڷ� ������ ������
		System.out.printf("num : %d\n", num);
		
	}

}