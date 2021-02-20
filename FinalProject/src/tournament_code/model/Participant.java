package tournament_code.model;

public class Participant {

	private String name;

	public Participant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Participant)) {
			return false;
		} else {
			Participant p = (Participant) other;
			return p.getName().equals(name);
		}
	}

	@Override
	public String toString() {
		return "Participant's name:" + name;
	}

}
