package com.Toekn;

import java.util.StringTokenizer;

/*
 * 
 * https://mondaymonday2.tistory.com/m/268
 * 
 */

public class ToeknExam {

	public static void main(String[] args) {
		String text = "ȫ�浿/�̼�ȫ/�ڿ���";
		
		StringTokenizer st = new StringTokenizer(text,"/");
		// "/" �������� �и���
		int countTokens = st.countTokens();
		for (int i = 0 ; i < countTokens; i++) {
			String token = st.nextToken(); // ��ū �Ѱ� pop
			System.out.println(token);
		}
		
		System.out.println();

			st = new StringTokenizer(text,"/");
			while(st.hasMoreTokens()) { // ��ū�� �����ִ°�
				String token = st.nextToken();
				System.out.println(token);
			}

	}

}
