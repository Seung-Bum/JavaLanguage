package TestClass;

public class LoadClass { // �������� �ε�� Ŭ���� �Դϴ�.

	private static String staticstr;
	static { staticstr = "���� ���� �ε� �Ǿ����ϴ�.";	}
	
	public LoadClass() {
		System.out.println("���� ��ü�� ���� �Ǿ����� �����ڰ� ȣ��Ǿ����ϴ�.");
		this.setStaticstr("���� �ε� ������ ��ü ���� ��");
	}

	public static String getStaticstr() {
		return LoadClass.staticstr;
	}

	private void setStaticstr(String staricStr) {
		System.out.println(staricStr + " ����Ϸ�");
		this.staticstr = staricStr;
	}
	
	public void print() {
		// �ܼ� ���
		System.out.println("print");
    }
	
	// �Լ� ���� (�Ķ���Ͱ� String Ÿ�� �ϳ� ����)
	public void print(String msg) {
	    // �ܼ� ���
	    System.out.println("print " + msg);
	}
	
	// �Լ� ���� (�Ķ���Ͱ� String, int Ÿ�� ����)
	public void print(String msg, int count) {
	    // �ܼ� ���
	    System.out.println("print " + msg + " count - " + count);
	}

}



