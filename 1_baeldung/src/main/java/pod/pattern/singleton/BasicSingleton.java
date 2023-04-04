package pod.pattern.singleton;

/**
 * Reference: https://www.baeldung.com/java-singleton
 */
public final class BasicSingleton {

	private static BasicSingleton INSTANCE;

	private String info = "Initial info";

	private BasicSingleton() {
	}

	public static BasicSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BasicSingleton();
		}
		return INSTANCE;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
