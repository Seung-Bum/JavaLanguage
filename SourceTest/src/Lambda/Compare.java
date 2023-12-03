package Lambda;

import java.util.ArrayList;
import java.util.Comparator;

public class Compare {

	public static void main(String[] args) {
		
		// Type types, Double height, Double weight
		Pokemon �̻��ؾ� = new Pokemon("�̻��ؾ�", new Type("poison", "grass"), 0.7, 6.9);
		Pokemon ���̸� = new Pokemon("���̸�", new Type("fire"), 0.6, 8.5);
		Pokemon ���α� = new Pokemon("���α�", new Type("water"), 0.5, 9.0);
		
		System.out.println("�̻��ؾ� type : " + �̻��ؾ�.getType());
		System.out.println("�̻��ؾ� height : " + �̻��ؾ�.getHeight());
		System.out.println("�̻��ؾ� weight : " + �̻��ؾ�.getWeight());
		
		CompWeight compWeight = new CompWeight();
		//CompHeight compHeight = new CompHeight();
		//System.out.println(compWeight.compare(���̸�, ���α�)); // ���غ��� ������ -1, ������ 0, ũ�� 1
		//System.out.println(compHeight.compare(���̸�, ���α�));
		
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(���α�);
		pokemonList.add(�̻��ؾ�);
		pokemonList.add(���̸�);
		
		// 1. ���
		pokemonList.sort(compWeight);
		
		System.out.println("pokemonList : ������ ��������");
		for(Pokemon pokemon: pokemonList) {
			System.out.println(pokemon.getName() + " " + pokemon.getWeight());
		}
		
		// 3. lambda ��������
		pokemonList.sort(new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		// 4. lambda ��������
		pokemonList.sort(
				(Pokemon p1, Pokemon p2) -> {return p1.getHeight().compareTo(p2.getHeight());}
		);
		
		// 5. Ÿ��ǥ�� ����
		pokemonList.sort((p1, p2) -> {return p1.getHeight().compareTo(p2.getHeight())*-1;});
		System.out.println("pokemonList : Ű ��������");
		for(Pokemon pokemon: pokemonList) {
			System.out.println(pokemon.getName() + " " + pokemon.getHeight());
		}
	}
	
	public static class CompWeight implements Comparator<Pokemon> {
		@Override
		public int compare(Pokemon w1, Pokemon w2) {
			return w1.getWeight().compareTo(w2.getWeight());
		}
	}
	
	// 1. lambda ��������
	public static class CompHeight implements Comparator<Pokemon> {
		@Override
		public int compare(Pokemon h1, Pokemon h2) {
			return h1.getHeight().compareTo(h2.getHeight());
		}
	}
	
	// 2. lambda ��������
	Comparator<Pokemon> cmp = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			return 0;
		}
	};
	
}
