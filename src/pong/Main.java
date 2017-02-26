package pong;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pong.model.en.viewResources;
import pong.view.StageController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private StageController myController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			myController=new StageController();
			loadPrimaryView();
			loadRankListView();
			loadPlayerInforView();
			myController.getStage(viewResources.ouLayer.getName()).setOnCloseRequest(e->handleCLoseAllStage());
			myController.setStage(viewResources.ouLayer.getName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void handleCLoseAllStage() {
		System.exit(0);
	}

	private void loadPrimaryView(){
		myController.loadStage(viewResources.ouLayer.getName(), viewResources.ouLayer.getResource());
	}
	
	private void loadRankListView(){
		myController.loadStage(viewResources.rankList.getName(), viewResources.rankList.getResource(), StageStyle.UNDECORATED);
	}
	
	private void loadPlayerInforView() {
		myController.loadStage(viewResources.playerInfor.getName(), viewResources.playerInfor.getResource(), StageStyle.UNDECORATED);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
