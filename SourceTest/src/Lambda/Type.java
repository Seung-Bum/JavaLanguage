package Lambda;

import java.util.Arrays;

public class Type {
	
	private String[] Types;
	
	public Type(String...type) {    // �Ű������� ���� ������
		this.Types = type;
	}

	@Override
	public String toString() {
		return "Type [Types=" + Arrays.toString(Types) + "]";
	}
	
}
