package TestClass;

public class LoadClass { // �������� �ε�� Ŭ���� �Դϴ�.

	private static String staticstr;
	static {
		staticstr = "���� ���� �ε� �Ǿ����ϴ�.";
	}

	public static String getStaticstr() {
		return LoadClass.staticstr;
	}

	public LoadClass() {
		System.out.println("���� ��ü�� ���� �Ǿ����� �����ڰ� ȣ��Ǿ����ϴ�.");
		this.setStaticstr("���� �ε� ������ ��ü ���� ��");
	}

	private void setStaticstr(String staricStr) {
		this.staticstr = staricStr;
		
	}

}



