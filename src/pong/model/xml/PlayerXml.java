package pong.model.xml;

public class PlayerXml {
	
	private String name;
	private Double score;

	public PlayerXml() {
		name="Player1";
		score=0.0;
	}

	public PlayerXml(String name, Double score){
		this.name=name;
		this.score=score;
	}

	public String getName() {
		return name;
	}

	public Double getScore() {
		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
