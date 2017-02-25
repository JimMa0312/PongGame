package pong.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends Player{
	
	private enum Direction{
		UP,DOWN;
	}
	
	Direction direction=Direction.UP;
	
	public Enemy(Image image, Pane layer, double x, double y, double r, double dx, double dy, double dr, double health,
			double damage, Input input, double speed) {
		super(image, layer, x, y, r, dx, dy, dr, health, damage, null, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInput() {
			checkRemovability();
			if (direction==Direction.UP) {
				dy=-speed;
			}else{
				dy=speed;
			}
	}
	
	@Override
	public void checkRemovability() {
		super.checkBounds();
		
		if (y==playerShipMinY) {
			direction=direction.DOWN;
		}else if (y==playerShipMaxY) {
			direction=direction.UP;
		}
	}
}
