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
		
		// 현재 커서 이후에 요소들이 있는지 여부 체크, 있으면 true
		while(em.hasMoreElements()) { 
			// 해당 데이터를 가져오고 커서가 다음 요소를 가리키게 한다.
			// em.nextElement() 메소드를 통해 해당 요소를 꺼내고 현재 커서를 ★다음 요소를 가르키게 한다.
			int val = em.nextElement(); 
			System.out.println(val);
		}
		
		ArrayList<String> lst = new ArrayList<>();
		lst.add("사과");
		lst.add("바나나");
		lst.add("키위");
		
		Iterator<String> it = lst.iterator();
		
		for(;it.hasNext();) {
			// 해당 데이터를 가져오고 커서가 다음 요소를 가리키게 한다.
			String str = it.next();
			System.out.println(str);       
		}
	}
}