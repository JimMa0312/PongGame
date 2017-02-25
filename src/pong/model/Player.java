package pong.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends SpriteBase{
	
	protected double playerShipMinX;
	protected double playerShipMaxX;
	protected double playerShipMinY;
	protected double playerShipMaxY;
	
	private Input input;
	
	protected double speed;

	

	public Player(Image image, Pane layer, double x, double y, double r, double dx, double dy, double dr, double health,
			double damage, Input input, double speed) {
		super(image, layer, x, y, r, dx, dy, dr, health, damage);
		this.input = input;
		this.speed = speed;
		
		init();
	}



	private void init() {
		playerShipMinX=0;
		playerShipMaxX=Setting.PANE_WIDTH-image.getWidth();
		playerShipMinY=playerShipMaxY=y;
	}
	
	public void processInput() {
		if (input.isMoveLeft()) {
			dx=-speed;
		}else if (input.isMoveRight()) {
			dx=speed;
		}else {
			dx=0d;
		}
		
		if (input.isMoveUp()) {
			dy=-speed;
		}else if (input.isMoveDown()) {
			dy=speed;
		}else {
			dy=0d;
		}
	}
	
	@Override
	public void move(){
		super.move();
		
		checkBounds();
	}
	
	public void checkBounds() {
		//垂直
		if (Double.compare(y, playerShipMinY)<0) {
			y=playerShipMinY;
		}else if (Double.compare(y, playerShipMaxY)>0) {
			y=playerShipMaxY;
		}
		
		if (Double.compare(x, playerShipMinX)<0) {
			x=playerShipMinX;
		}else if (Double.compare(x, playerShipMaxX)>0) {
			x=playerShipMaxX;
		}
	}

	@Override
	public void checkRemovability() {
		
	}

}
