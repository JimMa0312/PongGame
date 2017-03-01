package pong.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pong.Main;
import pong.model.Player;
import pong.model.en.viewResources;

public class PlayerInforController implements Initializable, ControlledStage {
	StageController myController;
	
	@FXML
	Label gradeLabel;
	@FXML
	TextField playerNameTextField;

	public PlayerInforController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setStageController(StageController stageController) {
		myController=stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gradeLabel.setText(getGameTime()+" S");
		playerNameTextField.setText("Player1");
	}
	
	@FXML
	private void handleOK(){
		
	}
	
	@FXML
	private void handleESC(){
		myController.shutDownStage(viewResources.playerInfor.getName());
	}
	
	private String getGameTime(){
		if (Main.getPlayers().size()>0) {
			Player player=Main.getPlayers().get(0);
			
			double millisecond=(int)(player.getEndTime()-player.getStartTime())*0.001;
			
			return Double.toString(millisecond);
		}
		return null;
	}
}
