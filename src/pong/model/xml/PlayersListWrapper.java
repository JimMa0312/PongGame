package pong.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="players")
public class PlayersListWrapper {
	
	private List<PlayerXml> players;
	
	@XmlElement(name="player")
	public List<PlayerXml> getPlayers(){
		return players;
	}
	
	public void setPlayers(List<PlayerXml> players){
		this.players=players;
	}
}
