package TestClass;

/* https://blog.hyper-link.kr/2011/11/24/java-class-for-name/
 * 
 * string [] args�� ��Ʈ�� Ÿ���� �迭�� args�� �����Ѱ����μ�
 * �ڹٸ� ����ÿ� �ִ� �μ����� �޽��ϴ�. (��Ģ)
 * ����ÿ� java Ŭ���� �̸��� a, b, c Ŭ������ ������
 * args[0]="a"; args[1]="b" �̷������� ���� �˴ϴ�.
 * 
 *  �Ｎ������� ���� ����.
	�Ｎ������� ������ �մ��� � ��� ������ ���ϴ��� �𸨴ϴ�.
	(� Ŭ������ ������ �𸥴�.)
	
	��ġ����� ���ϴ� �մ��� �ͼ� �ֹ��� �ϸ� ������ �� �ڸ����� ��ġ ����� ����� �ְ�,
	�Ϲݱ���� ���ϴ� �մ��� �ͼ� �ֹ��� �ϸ� ������ �� �ڸ����� �Ϲ� ����� ����� ����.
	(��� ������ 2���� �ۿ� ������ �ȳ��׿� ����)
	
	�� ������ � �մ��� ���� �𸣱� ������ ����� ����� �� �� ����
	( �̸� ����� �θ� �Ｎ����� �ƴϴϱ�.����)
	
	���⼭ �ڵ�� �ٽ� ���ư�����
	�̸� ����� �� ����� String str=new String(���̸� ����� �� ��䡱);
	�Ｎ���� ���� ����� Class.forName(���Ｎ���� ���� ��� Class��);
*/


// ������ args[0]�� �ȵ��ͼ� (���´ٰ�� ��������) ���� ���ڸ��� Ŭ���� �̸��� �־ ������
public class TestClass {
	public static void main(String args[]) {

			try {
				Class classExample = null;
				classExample = Class.forName(TestClass.class.getPackage().getName() + ".LoadClass"); // �޸𸮷� �ε�(���������)

				System.out.println(LoadClass.getStaticstr());	   // ���� ���� �ε� �Ǿ����ϴ�. 

				try {

					Object obj = classExample.newInstance();       // ���⼭ ��ü�� ���� ��. (������ȣ��)
					System.out.println(LoadClass.getStaticstr());  // ���� �ε� ������ ��ü ���� ��

				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
}
/* ��°��
���� ���� �ε� �Ǿ����ϴ�.
���� ��ü�� ���� �Ǿ����� �����ڰ� ȣ��Ǿ����ϴ�.
���� �ε� ������ ��ü ���� ��
*/