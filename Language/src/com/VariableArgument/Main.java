package com.VariableArgument;
/*
 * ��������
 * ���ڰ��� ������ ������� �־ ����� �� �ִٴ� ��
 * �������ڴ� �Ķ������ ������ �������� �־�� ������ ������ ���� �ʴ´ٰ���
 * �����ϸ� �������ڸ� ����� �޼���� �����ε� ���� �ʴ� ���� ����
 * 
 */


public class Main {

	public static void main(String[] args) {
		String t1 ="1";
		String t2 ="2";
		String t3 ="3";
		String t4 ="4";
		String t5 ="5";
		
		// �پ��� ���·� ���ڸ� �־ ���� �߻����� ����
		//test(); // ����־ Ok
		//test(new String[] {"a", "b"}); // �迭�̾ OK
		test(t1, t2, t3, t4, t5);

	}
	
	public static void test (String ... strings) {

		  System.out.println();
		  System.out.println("������������������������������");
		  System.out.println(strings.length + "EA");

		  for (int i = 0; i < strings.length; i++) {
		    System.out.println(strings[i]);
		  }

		  System.out.println("������������������������������");

	}
}
