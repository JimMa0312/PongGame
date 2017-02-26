package pong.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pong.Main;
import pong.config.Setting;
import pong.model.Ball;
import pong.model.Input;
import pong.model.Player;
import pong.model.SpriteBase;
import pong.model.en.viewResources;

public class OutlayerController implements Initializable, ControlledStage {
	Random random = new Random();
	AnimationTimer gameloop;
	
	@FXML
	AnchorPane gamePane;

	Image playerImage;
	Image ballImage;

	List<Player> players = new ArrayList<>();
	List<Ball> balls = new ArrayList<>();

	private StageController myStageController;
	@FXML
	private BorderPane primaryPane;

	@Override
	public void setStageController(StageController stageController) {
		this.myStageController = stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.setPlayers(players);
	}

	@FXML
	private void handleCLoseAllStage() {
		System.exit(0);                                                                                
	}

	@FXML
	private void handleStartGame() {
		System.out.println("Game Start");

		loadGame();
		createPlayers();
		spawnBalls();
		gameloop = new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				players.forEach(sprite->sprite.processInput());
				players.forEach(sprite->sprite.checkRemovability());
				
				players.forEach(sprite->sprite.move());
				balls.forEach(sprite -> sprite.move());
				
				checkCollosons();

				players.forEach(sprite->sprite.updateUI());
				balls.forEach(sprite->sprite.updateUI());
				
				balls.forEach(sprite->sprite.checkRemovability());
				
				removeSprites(balls);
				
				gameOver();
			}
		};

		gameloop.start();
		players.get(0).setStartTime(System.currentTimeMillis());
		System.out.println(players.get(0).getStartTime());
	}

	@FXML
	private void handleShowRankList() {
		myStageController.setStage(viewResources.rankList.getName());
	}
	
	@FXML
	private void handleStopGame(){
		gameOver();
	}

	/*
	 * 加载游戏，绘制像素，并储存成Image
	 */
	private void loadGame() {
		WritableImage wImage;
		double w = Setting.PADDLE_WIDTH;
		double h = Setting.PADDLE_HEIGHT;

		Rectangle rectangle = new Rectangle(w, h);

		wImage = new WritableImage((int) w, (int) h);
		rectangle.snapshot(null, wImage);

		playerImage = wImage;

		double r = Setting.BALL_RADIUS;
		Circle circle = new Circle(r);

		wImage = new WritableImage((int) r * 2, (int) r * 2);
		circle.snapshot(null, wImage);

		ballImage = wImage;
	}
	private void createPlayers(){
		players.add(createPlayer());
	}
	private Player createPlayer() {
		Input input = new Input(myStageController.getStage(viewResources.ouLayer.getName()).getScene());
		input.addListeners();
		Image image = playerImage;

		double x = (Setting.PANE_WIDTH - image.getWidth()) * 0.5;
		double y = Setting.PANE_HEIGHT - Setting.PADDLE_OFFSET_y;

		Player player = new Player(playerImage, gamePane, x, y, 0, 0, 0, 0, 1, 0, input, Setting.PADDLE_SPEED);

		return player;
	}

	private void spawnBalls() {
		if (balls.size() == 0) {
			createBall();
		}
	}

	private void createBall() {
		Image image = ballImage;

		double x = (Setting.PANE_WIDTH - 2 * image.getWidth()) * 0.5;
		double y = (Setting.PANE_HEIGHT - playerImage.getHeight() - 2 * image.getHeight()) * 0.5;

		Ball ball = new Ball(image, gamePane, x, y, 0, 0, Setting.BALL_SPEED, 0, 1, 1);

		balls.add(ball);
	}

	protected void checkCollosons() {
		for (Player player : players) {
			for (Ball ball : balls) {
				if (player.collidesWith(ball)) {
					ball.bounceOff(player);
					
					
				}
			}
		}
	}
	
	private void removeSprites(List<? extends SpriteBase> spriteList) {
		Iterator<? extends SpriteBase> iter = spriteList.iterator();
		while (iter.hasNext()) {
			SpriteBase spriteBase = iter.next();
			if (spriteBase.isRemovable()) {
				spriteBase.removeFromLayer();
				iter.remove();
			}
		}
	}
	
	private void gameOver() {
		if (balls.size()==0) {
			players.get(0).setEndTime(System.currentTimeMillis());
			gameloop.stop();
			gameloop=null;
			System.out.println("Game Over");
			gamePane.getChildren().clear();
			
			myStageController.setStage(viewResources.playerInfor.getName());
		}
	}
}
