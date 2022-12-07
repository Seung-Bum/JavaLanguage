package TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* https://blog.hyper-link.kr/2011/11/24/java-class-for-name/
 * https://www.javatpoint.com/java-class-getmethod-method
 * https://carrotweb.tistory.com/56
 * �� https://nowonbun.tistory.com/519 
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
					
					Object obj = classExample.newInstance();       // ���⼭ ��ü�� ���� ��. (LoadClass ������ȣ��)
					System.out.println(LoadClass.getStaticstr());  // ���� �ε� ������ ��ü ���� ��
					Class cls = obj.getClass();
					
					try {
						// method�� object���� �ٷ� ���� ���ϰ� class�� ���ľ��Ѵ�.
						Method method = cls.getMethod("getStaticstr", null);  
				    	Method method2 = cls.getMethod("print");
				    	Method method3 = cls.getMethod("print", String.class);
				    	Method method4 = cls.getMethod("print", String.class, Integer.TYPE);
						//System.out.println(method.toString());
						
						// ����� �Լ��� ������ �ν��Ͻ��� �ְ� �����Ų��.
					    try {
					    	method2.invoke(obj);
					    	method3.invoke(obj, "test");
					    	method4.invoke(obj, "test2", 100);
					    	System.out.println( method.invoke(obj) ); // ���� �ε� ������ ��ü ���� ���� ����Ʈ
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					    
					    
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}

				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
}
/* ��°��
���� ���� �ε� �Ǿ����ϴ�.
���� ��ü�� ���� �Ǿ����� �����ڰ� ȣ��Ǿ����ϴ�.
���� �ε� ������ ��ü ���� ��
*/