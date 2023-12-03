package Lambda;

public class Pokemon {
	
	private Type type;
	private Double height;
	private Double weight;
	private String name;
	
	public Pokemon(String name, Type types, Double height, Double weight) {    // 매개변수를 가진 생성자
		this.name = name;
		this.type = types;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
