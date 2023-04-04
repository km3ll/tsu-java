package pod.pattern.builder.basic;

/**
 * Reference: https://www.baeldung.com/java-builder-pattern-freebuilder
 */
public class Employee {

	private long id;
	private String name;
	private int age;

	private Employee() {
	}

	private Employee(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public static class Builder {

		private long id;
		private String name;
		private int age;

		public Builder withId(long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAge(int age) {
			this.age = age;
			return this;
		}

		public Employee build() {
			return new Employee(id, name, age);
		}

	}

}
