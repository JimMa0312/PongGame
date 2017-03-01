package pong;
	
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pong.model.Player;
import pong.model.en.viewResources;
import pong.model.xml.PlayerXml;
import pong.model.xml.PlayersListWrapper;
import pong.view.StageController;


public class Main extends Application {
	
	private static StageController myController;
	private static List<Player> players;
	private static List<PlayerXml> playersWithXml=new ArrayList<>();
	
	public static String path = "Players.xml";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			myController=new StageController();
			loadPrimaryView();
			myController.getStage(viewResources.ouLayer.getName()).setOnCloseRequest(e->handleCLoseAllStage());
			myController.getStage(viewResources.ouLayer.getName()).setTitle("弹球游戏");
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
	
	public static void loadRankListView(){
		myController.loadStage(viewResources.rankList.getName(), viewResources.rankList.getResource(), StageStyle.UNDECORATED);
	}
	
	public static void loadPlayerInforView() {
		myController.loadStage(viewResources.playerInfor.getName(), viewResources.playerInfor.getResource(), StageStyle.UNDECORATED);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static void setPlayers(List<Player> players) {
		Main.players = players;
	}

	public static List<PlayerXml> getPlayersWithXml() {
		return playersWithXml;
	}

	public static void setPlayersWithXml(List<PlayerXml> playersWithXml) {
		Main.playersWithXml = playersWithXml;
	}
	public static void savePlayInfor(File file) {
		try {
			JAXBContext context=JAXBContext.newInstance(PlayersListWrapper.class);
			Marshaller marshaller=context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			PlayersListWrapper wrapper=new PlayersListWrapper();
			wrapper.setPlayers(playersWithXml);
			
			marshaller.marshal(wrapper, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadPlayersFromFile(File file){
		try {
			JAXBContext context=JAXBContext.newInstance(PlayersListWrapper.class);
			
			Unmarshaller unmarshaller=context.createUnmarshaller();
			
			PlayersListWrapper wrapper=(PlayersListWrapper) unmarshaller.unmarshal(file);
			
			playersWithXml.clear();
			playersWithXml.addAll(wrapper.getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveXml(){
		File file=new File(path);
		savePlayInfor(file);
	}
	
	public static void LoadXml(){
		File file=new File(path);
		loadPlayersFromFile(file);
	}
}
