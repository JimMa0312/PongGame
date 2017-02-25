package pong.model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Ball extends SpriteBase{
	Random random=new Random();

	public Ball(Image image, Pane layer, double x, double y, double r, double dx, double dy, double dr,
			double health, double damage) {
		super(image, layer, x, y, r, dx, dy, dr, health, damage);
	}
	
	public void bounceOff(SpriteBase sprite) {
		dy=-dy;
		dx=(random.nextInt(3)+2) % 5;
		if (random.nextBoolean()) {
			dx=-dx;
		}
	}
	
	@Override
	public void move() {
		super.move();
		
		checkBorder();
	}
	
	public void checkBorder(){
		if (Double.compare(getY(), 0)<=0) {
			dy=-dy;
		}
		if (Double.compare(getX(), 0)<=0||Double.compare(getX(), Setting.PANE_WIDTH-Setting.BALL_RADIUS*2)>=0) {
			dx=-dx;
		}
	}

	@Override
	public void checkRemovability() {
		if (Double.compare(Setting.PANE_HEIGHT, getY())<=0) {
			setRemovable(true);
		}
	}

}
