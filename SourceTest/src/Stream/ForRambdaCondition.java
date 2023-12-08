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
				   .filter(i-> i%2==1) // Ȧ�� ���͸�
				   .map(i -> i*i) // Ȧ������ ����
				   .collect(Collectors.toList()); // Ȧ������ ������ �����͸� �ٽ� ilist�� ����
		ilist.forEach(i->{System.out.printf(i+"\t");});
		System.out.println();
		//stream reduce 
		s=ilist.stream().reduce(0, Integer::sum); // Ȧ�� �������� �ձ��ϱ� (0���� ����)
		System.out.println("1~10������ Ȧ���� ���� ������: "+s);
	}
}


