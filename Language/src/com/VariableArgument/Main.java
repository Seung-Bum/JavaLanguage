package com.VariableArgument;
/*
 * 가변인자
 * 인자값을 개수에 상관없이 넣어서 사용할 수 있다는 말
 * 가변인자는 파라미터의 순서중 마지막에 있어야 컴파일 에러가 나지 않는다고함
 * 가능하면 가변인자를 사용한 메서드는 오버로딩 하지 않는 것이 좋음
 * 
 */


public class Main {

	public static void main(String[] args) {
		String t1 ="1";
		String t2 ="2";
		String t3 ="3";
		String t4 ="4";
		String t5 ="5";
		
		// 다양한 형태로 인자를 넣어도 오류 발생하지 않음
		//test(); // 비어있어도 Ok
		//test(new String[] {"a", "b"}); // 배열이어도 OK
		test(t1, t2, t3, t4, t5);

	}
	
	public static void test (String ... strings) {

		  System.out.println();
		  System.out.println("───────────────");
		  System.out.println(strings.length + "EA");

		  for (int i = 0; i < strings.length; i++) {
		    System.out.println(strings[i]);
		  }

		  System.out.println("───────────────");

	}
}
