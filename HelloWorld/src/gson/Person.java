package gson;

public class Person {

	private String name;
	private String email;

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nEmail: " + email;
	}

}
