package pong.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author JimMa
 *
 */
public abstract class SpriteBase {

	protected Image image;
	protected ImageView imageView;

	private Pane layer;

	protected double x;
	protected double y;
	protected double r;
	protected double dx;
	protected double dy;
	protected double dr;

	private double health;
	private double damage;

	private boolean removable = false;

	private double w;
	private double h;

	private boolean canMove = true;

	public SpriteBase() {
		// TODO Auto-generated constructor stub
	}

	public SpriteBase(Image image, Pane layer, double x, double y, double r, double dx, double dy, double dr,
			double health, double damage) {
		super();
		this.image = image;
		this.layer = layer;
		this.x = x;
		this.y = y;
		this.r = r;
		this.dx = dx;
		this.dy = dy;
		this.dr = dr;
		this.health = health;
		this.damage = damage;

		this.imageView = new ImageView(image);
		this.imageView.relocate(x, y);
		this.imageView.setRotate(r);

		this.w = image.getWidth();
		// imageView.getBoundsInParent().getWidth();
		this.h = image.getHeight();
		// imageView.getBoundsInParent().getHeight();

		addToLayer();
	}

	public void addToLayer() {
		this.layer.getChildren().add(this.imageView);
	}

	public void removeFromLayer() {
		this.layer.getChildren().remove(this.imageView);
	}

	public Pane getLayer() {
		return layer;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public double getDr() {
		return dr;
	}

	public double getHealth() {
		return health;
	}

	public double getDamage() {
		return damage;
	}

	public boolean isRemovable() {
		return removable;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void setDr(double dr) {
		this.dr = dr;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
	
	public boolean isAlive(){
		return Double.compare(health, 0)>0;
	}
	
	public ImageView getView(){
		return imageView;
	}
	
	public void updateUI() {
		imageView.relocate(x, y);
		imageView.setRotate(r);
	}
	
	public double getWidth() {
		return w;
	}
	
	public double getHeight() {
		return h;
	}
	
	public double getCenterX() {
		return x+w*0.5;
	}
	
	public double getCenterY() {
		return y+h*0.5;
	}
	
	public boolean collidesWith(SpriteBase otherSprite) {
		return (otherSprite.x+otherSprite.w>=x&&otherSprite.y+otherSprite.h>=y&&otherSprite.x<=x+w&&otherSprite.y<=y+h);
	}
	
	public void getDamagedBy(SpriteBase sprite) {
		health-=sprite.getDamage();
	}
	
	public void kill(){
		setHealth(0);
	}
	
	public void remove(){
		setRemovable(true);
	}
	
	public void stopMovement(){
		this.canMove=false;
	}

	
	public void move(){
		if (!canMove) {
			return;
		}
		
		x+=dx;
		y+=dy;
		r+=dr;
	}
	
	public abstract void checkRemovability();
}
