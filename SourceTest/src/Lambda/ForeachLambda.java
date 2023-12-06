package Lambda;

import java.util.Arrays;
import java.util.List;

public class ForeachLambda { 
	
	public static void main(String[] args) {
		List<Integer> mmLists = Arrays.asList(1,2,3,4,5,6);
		mmLists.forEach(
				m -> { System.out.println(m+"\t"); }
		);		
	}
}
