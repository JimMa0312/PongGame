package pong.util;

import java.util.Comparator;

import pong.model.xml.PlayerXml;

public class StepComparator implements Comparator<PlayerXml> {

	@Override
	public int compare(PlayerXml o1, PlayerXml o2) {
		if (o1.getScore()<o2.getScore()) {
			return 1;
		}else if (o1.getScore()==o2.getScore()) {
			return 0;
		}else {
			return -1;
		}
	}

}
