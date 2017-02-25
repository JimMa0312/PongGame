package pong.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class RankListController implements ControlledStage, Initializable {
	StageController myController;

	public RankListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStageController(StageController stageController) {
		myController=stageController;
	}

}
