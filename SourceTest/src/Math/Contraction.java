package Math;

// ��� ������ (�� �ڸ����� �� ���ϱ�)
public class Contraction {
	public static int sumEach(int n){
		int tot=0;
		while(n!=0){
			tot+=n%10; // 3 -> 2-> 1
			System.out.println("tot : " + tot);
			
			n/=10;     // 123-> 12-> 1->0
			System.out.println("n : " + n);
		}
		return tot;
	}
	public static void main(String[] args) {
		int number=123;
		int value=sumEach(number);
		System.out.printf("%d�� ���� ���ڸ��� �� ��: %d",number,value);
	}
}
