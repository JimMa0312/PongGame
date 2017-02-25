package pong.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pong.model.Setting;
import pong.model.en.viewResources;

public class OutlayerController implements Initializable, ControlledStage {
	Random random=new Random();
	
	AnchorPane gamePane;
	
	private StageController myStageController;
	@FXML
	private BorderPane primaryPane;
	
	@Override
	public void setStageController(StageController stageController) {
		this.myStageController=stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	private void handleCLoseAllStage() {
		System.exit(0);
	}
	
	@FXML
	private void handleStartGame(){
		gamePane=new AnchorPane();
		gamePane.setPrefSize(Setting.PANE_WIDTH, Setting.PANE_HEIGHT);
	}

	@FXML
	private void handleShowRankList(){
		myStageController.setStage(viewResources.rankList.getName());
	}
}
