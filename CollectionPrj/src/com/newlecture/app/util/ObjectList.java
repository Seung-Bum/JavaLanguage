package com.newlecture.app.util;

// Object를 사용하여
// int뿐 아니라 다른 객체에도 대응할 수 있게 되었다.
public class ObjectList {
	
	private Object[] nums;
	private int current;
	
	public ObjectList() {
		nums = new Object[3];
		current = 0; 
	}

	public void add(int num) {
		nums[current] = num;
		current++;
	}

	public void clear() {
//		for(int i=0; i < current; i++)
//			nums[i] = 0;
//		
//		nums = new int[3];
		// 이 방법으로 하면 기존 배열 참조가 안되면서 기존 배열은 가비지 컬렉터가 수거함
		
		current = 0;
		// current의 공간이 없다고 한다면
		// current의 값을 가지고 몇개인지 알아보고 값의 위치를 저장하기 때문에
		// 0으로 지정해 주면 된다.
		// c가 가르키는 위치에서 값이 변경된다.
		
	}

	public int size() {
		return current;
	}

	public Object get(int index) {
		if(current <= index)
			throw new IndexOutOfBoundsException();
		return nums[index];
	}
	// current는 현재 데이터의 개수를 뜻한다.
	// current가 3일 경우 index는 0,1,2가 된다.
	// index가 current보다 클 수가 없고 같을수도 없다.
	// 만약에 같거나 크다면 입력 오류 이므로 예외 발생한다.

}
