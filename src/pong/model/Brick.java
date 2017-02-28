package pong.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Brick extends SpriteBase {

	public Brick() {
		// TODO Auto-generated constructor stub
	}

	public Brick(Image image, Pane layer, double x, double y, double r, double dx, double dy, double dr, double health,
			double damage) {
		super(image, layer, x, y, r, dx, dy, dr, health, damage);
	}

	@Override
	public void checkRemovability() {
		
	}

}
