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
		dx=-dx;
		dy=(random.nextInt(3)+2) % 5;
		if (random.nextBoolean()) {
			dy=-dy;
		}
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
		
		checkYBorder();
	}
	
	public void checkYBorder(){
		if (Double.compare(getY(), 0)<=0||
				Double.compare(getY(), Setting.PANE_HEIGHT-Setting.BALL_RADIUS*2)>=0) {
			dy=-dy;
		}
	}

	@Override
	public void checkRemovability() {
		if (Double.compare(getX(), 0)<0||
				Double.compare(getX(), Setting.PANE_WIDTH)>0) {
			setRemovable(true);
		}
	}

}
