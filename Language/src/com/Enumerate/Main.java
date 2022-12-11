package com.Enumerate;

import java.util.*;

/*
 * Enumeration / iterator
 * https://byungmin.tistory.com/m/13
 * 
 */

public class Main {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		v.add(3);
		v.add(12);
		v.add(51);
		
		Enumeration<Integer> em = v.elements();
		
		// ���� Ŀ�� ���Ŀ� ��ҵ��� �ִ��� ���� üũ, ������ true
		while(em.hasMoreElements()) { 
			// �ش� �����͸� �������� Ŀ���� ���� ��Ҹ� ����Ű�� �Ѵ�.
			// em.nextElement() �޼ҵ带 ���� �ش� ��Ҹ� ������ ���� Ŀ���� �ڴ��� ��Ҹ� ����Ű�� �Ѵ�.
			int val = em.nextElement(); 
			System.out.println(val);
		}
		
		ArrayList<String> lst = new ArrayList<>();
		lst.add("���");
		lst.add("�ٳ���");
		lst.add("Ű��");
		
		Iterator<String> it = lst.iterator();
		
		for(;it.hasNext();) {
			// �ش� �����͸� �������� Ŀ���� ���� ��Ҹ� ����Ű�� �Ѵ�.
			String str = it.next();
			System.out.println(str);       
		}
	}
}