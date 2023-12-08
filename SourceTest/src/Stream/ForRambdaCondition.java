package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForRambdaCondition {
	public static void main(String[] args) {
		List<Integer> ilist=new ArrayList<>();
		int s=0;
		for (int i = 1; i <= 10; i++) {
			ilist.add(i);
		}
		// stream filter map collect
		ilist=ilist.stream()
				   .filter(i-> i%2==1) // 홀수 필터링
				   .map(i -> i*i) // 홀수끼리 제곱
				   .collect(Collectors.toList()); // 홀수끼리 제곱한 데이터를 다시 ilist에 넣음
		ilist.forEach(i->{System.out.printf(i+"\t");});
		System.out.println();
		//stream reduce 
		s=ilist.stream().reduce(0, Integer::sum); // 홀수 제곱수의 합구하기 (0부터 시작)
		System.out.println("1~10사이의 홀수에 대한 제곱합: "+s);
	}
}


