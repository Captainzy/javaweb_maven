package springFramework.typeConversion;

public class Child extends Animal{
	private String species;
	private String name;
	private String age;
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "species :"+this.species+"\nname :"+this.name+"\nage :"+this.age;
	}
	
	
}
