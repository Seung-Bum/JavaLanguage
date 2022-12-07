package TestClass;

public class LoadClass { // 동적으로 로드될 클래스 입니다.

	private static String staticstr;
	static {
		staticstr = "정적 변수 로딩 되었습니다.";
	}

	public static String getStaticstr() {
		return LoadClass.staticstr;
	}

	public LoadClass() {
		System.out.println("이제 객체가 생성 되었으며 생성자가 호출되었습니다.");
		this.setStaticstr("동적 로딩 성공과 객체 생성 됨");
	}

	private void setStaticstr(String staricStr) {
		this.staticstr = staricStr;
		
	}

}



