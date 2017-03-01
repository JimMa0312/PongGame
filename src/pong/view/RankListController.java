package pong.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pong.Main;
import pong.model.en.viewResources;
import pong.model.xml.PlayerXml;

public class RankListController implements ControlledStage, Initializable {
	StageController myController;
	@FXML
	private Label player1_name;
	@FXML
	private Label player1_score;
	@FXML
	private Label player2_name;
	@FXML
	private Label player2_score;
	@FXML
	private Label player3_name;
	@FXML
	private Label player3_score;
	@FXML
	private Label player4_name;
	@FXML
	private Label player4_score;
	@FXML
	private Label player5_name;
	@FXML
	private Label player5_score;
	@FXML
	private Label player6_name;
	@FXML
	private Label player6_score;
	@FXML
	private Label player7_name;
	@FXML
	private Label player7_score;
	@FXML
	private Label player8_name;
	@FXML
	private Label player8_score;
	@FXML
	private Label player9_name;
	@FXML
	private Label player9_score;
	@FXML
	private Label player10_name;
	@FXML
	private Label player10_score;
	private List<Label> names=new ArrayList<>();
	private List<Label> scores=new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initList();
		initContent();
		printRankList();
	}
	
	void initList(){
		names.add(player1_name);
		names.add(player2_name);
		names.add(player3_name);
		names.add(player4_name);
		names.add(player5_name);
		names.add(player6_name);
		names.add(player7_name);
		names.add(player8_name);
		names.add(player9_name);
		names.add(player10_name);
		scores.add(player1_score);
		scores.add(player2_score);
		scores.add(player3_score);
		scores.add(player4_score);
		scores.add(player5_score);
		scores.add(player6_score);
		scores.add(player7_score);
		scores.add(player8_score);
		scores.add(player9_score);
		scores.add(player10_score);
	}
	
	private void initContent(){
		for(int i=0;i<names.size();i++){
			names.get(i).setText("");
			scores.get(i).setText("");
		}
	}
	
	private void printRankList(){
		if (Main.getPlayersWithXml().size()==0) {
			Main.LoadXml();
		}
		List<PlayerXml> list=Main.getPlayersWithXml();
		for (int i = 0; i < list.size(); i++) {
			names.get(i).setText(list.get(i).getName());
			scores.get(i).setText(list.get(i).getScore()+" S");
		}
	}

	@Override
	public void setStageController(StageController stageController) {
		myController=stageController;
	}
	
	@FXML
	private void handleCloseStage(){
		myController.shutDownStage(viewResources.rankList.getName());
		myController.unloadStage(viewResources.rankList.getName());
	}
}
