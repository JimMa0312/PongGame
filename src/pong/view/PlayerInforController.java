package pong.view;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pong.Main;
import pong.model.Player;
import pong.model.en.viewResources;
import pong.model.xml.PlayerXml;
import pong.util.StepComparator;

public class PlayerInforController implements Initializable, ControlledStage {
	StageController myController;
	
	PlayerXml player;
	
	@FXML
	Label gradeLabel;
	@FXML
	TextField playerNameTextField;
	@FXML
	Button okButton;

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
		
		playerNameTextField.textProperty().addListener(ov->{
			if (playerNameTextField.getText().isEmpty()) {
				okButton.setDisable(true);
			}else{
				okButton.setDisable(false);
			}
		});
	}
	
	@FXML
	private void handleOK(){
		player=new PlayerXml(playerNameTextField.getText(), getGameTime());
		File file=new File(Main.path);
		if (file.exists()) {
			Main.LoadXml();
		}
		List<PlayerXml> playersWithxml=Main.getPlayersWithXml();
		playersWithxml.add(player);
		StepComparator stepComparator=new StepComparator();
		Collections.sort(playersWithxml, stepComparator);
		
		if (playersWithxml.size()>10) {
			for(int i=10;i<playersWithxml.size()-1;i++){
				playersWithxml.remove(i);
			}
		}
		Main.SaveXml();
		
		closeThisStage();
		Main.loadRankListView();
		myController.setStage(viewResources.rankList.getName());
	}
	
	@FXML
	private void handleESC(){
		player=null;
		closeThisStage();
	}
	
	private void closeThisStage(){
		myController.shutDownStage(viewResources.playerInfor.getName());
		myController.unloadStage(viewResources.playerInfor.getName());
	}
	
	private Double getGameTime(){
		if (Main.getPlayers().size()>0) {
			Player player=Main.getPlayers().get(0);
			
			double millisecond=(int)(player.getEndTime()-player.getStartTime())*0.001;
			
			return millisecond;
		}
		return null;
	}
	
}
