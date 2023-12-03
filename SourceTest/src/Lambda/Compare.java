package Lambda;

import java.util.ArrayList;
import java.util.Comparator;

public class Compare {

	public static void main(String[] args) {
		
		// Type types, Double height, Double weight
		Pokemon 이상해씨 = new Pokemon("이상해씨", new Type("poison", "grass"), 0.7, 6.9);
		Pokemon 파이리 = new Pokemon("파이리", new Type("fire"), 0.6, 8.5);
		Pokemon 꼬부기 = new Pokemon("꼬부기", new Type("water"), 0.5, 9.0);
		
		System.out.println("이상해씨 type : " + 이상해씨.getType());
		System.out.println("이상해씨 height : " + 이상해씨.getHeight());
		System.out.println("이상해씨 weight : " + 이상해씨.getWeight());
		
		CompWeight compWeight = new CompWeight();
		//CompHeight compHeight = new CompHeight();
		//System.out.println(compWeight.compare(파이리, 꼬부기)); // 기준보다 작으면 -1, 같으면 0, 크면 1
		//System.out.println(compHeight.compare(파이리, 꼬부기));
		
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(꼬부기);
		pokemonList.add(이상해씨);
		pokemonList.add(파이리);
		
		// 1. 사용
		pokemonList.sort(compWeight);
		
		System.out.println("pokemonList : 몸무게 오름차순");
		for(Pokemon pokemon: pokemonList) {
			System.out.println(pokemon.getName() + " " + pokemon.getWeight());
		}
		
		// 3. lambda 변형과정
		pokemonList.sort(new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		// 4. lambda 변형과정
		pokemonList.sort(
				(Pokemon p1, Pokemon p2) -> {return p1.getHeight().compareTo(p2.getHeight());}
		);
		
		// 5. 타입표시 제거
		pokemonList.sort((p1, p2) -> {return p1.getHeight().compareTo(p2.getHeight())*-1;});
		System.out.println("pokemonList : 키 내림차순");
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
	
	// 1. lambda 변형과정
	public static class CompHeight implements Comparator<Pokemon> {
		@Override
		public int compare(Pokemon h1, Pokemon h2) {
			return h1.getHeight().compareTo(h2.getHeight());
		}
	}
	
	// 2. lambda 변형과정
	Comparator<Pokemon> cmp = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			return 0;
		}
	};
	
}
