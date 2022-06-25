package com.newlecture.app.util;

// Object�� ����Ͽ�
// int�� �ƴ϶� �ٸ� ��ü���� ������ �� �ְ� �Ǿ���.
public class GList<T> {
	
	private Object[] nums;
	private int current;
	private int capacity;
	private int amount;
	
	public GList() {
		current = 0;
		capacity = 3;
		amount = 5;
		nums = new Object[capacity];
	}

	public void add(T num) {
		
		// ������ ���� ��� amount��ŭ �þ��.
		if(capacity <= current) {
			 Object[] temp = new Object[capacity + amount];
			 for(int i =0; i < current; i++)
				 temp[i] = nums[i];
			 nums = temp;
			 capacity += amount;
		}
		
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

	@SuppressWarnings("unchecked")
	// �ڹٿ��� �ǰ����� �ʴ� ������ �ڵ� ���� ������ ��� �Ⱥ��̰���
	public T get(int index) {
		if(current <= index)
			throw new IndexOutOfBoundsException();
		return (T)nums[index];
	}
	// current�� ���� �������� ������ ���Ѵ�.
	// current�� 3�� ��� index�� 0,1,2�� �ȴ�.
	// index�� current���� Ŭ ���� ���� �������� ����.
	// ���࿡ ���ų� ũ�ٸ� �Է� ���� �̹Ƿ� ���� �߻��Ѵ�.

}
