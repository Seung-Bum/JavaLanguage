package com.newlecture.app.util;

public class IntList {
	
	private int[] nums;
	private int current;
	
	public IntList() {
		nums = new int[3];
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
		// �� ������� �ϸ� ���� �迭 ������ �ȵǸ鼭 ���� �迭�� ������ �÷��Ͱ� ������
		
		current = 0;
		// current�� ������ ���ٰ� �Ѵٸ�
		// current�� ���� ������ ����� �˾ƺ��� ���� ��ġ�� �����ϱ� ������
		// 0���� ������ �ָ� �ȴ�.
		// c�� ����Ű�� ��ġ���� ���� ����ȴ�.
		
	}

	public int size() {
		return current;
	}

	public int get(int index) {
		if(current <= index)
			throw new IndexOutOfBoundsException();
		return nums[index];
	}
	// current�� ���� �������� ������ ���Ѵ�.
	// current�� 3�� ��� index�� 0,1,2�� �ȴ�.
	// index�� current���� Ŭ ���� ���� �������� ����.
	// ���࿡ ���ų� ũ�ٸ� �Է� ���� �̹Ƿ� ���� �߻��Ѵ�.

}