package TestClass;

public class LoadClass { // 동적으로 로드될 클래스 입니다.

	private static String staticstr;
	static { staticstr = "정적 변수 로딩 되었습니다.";	}
	
	public LoadClass() {
		System.out.println("이제 객체가 생성 되었으며 생성자가 호출되었습니다.");
		this.setStaticstr("동적 로딩 성공과 객체 생성 됨");
	}

	public static String getStaticstr() {
		return LoadClass.staticstr;
	}

	private void setStaticstr(String staricStr) {
		System.out.println(staricStr + " 변경완료");
		this.staticstr = staricStr;
	}
	
	public void print() {
		// 콘솔 출력
		System.out.println("print");
    }
	
	// 함수 생성 (파라미터가 String 타입 하나 있음)
	public void print(String msg) {
	    // 콘솔 출력
	    System.out.println("print " + msg);
	}
	
	// 함수 생성 (파라미터가 String, int 타입 있음)
	public void print(String msg, int count) {
	    // 콘솔 출력
	    System.out.println("print " + msg + " count - " + count);
	}

}



