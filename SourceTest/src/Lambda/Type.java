package Lambda;

import java.util.Arrays;

public class Type {
	
	private String[] Types;
	
	public Type(String...type) {    // 매개변수를 가진 생성자
		this.Types = type;
	}

	@Override
	public String toString() {
		return "Type [Types=" + Arrays.toString(Types) + "]";
	}
	
}
