package pong.model.en;

public enum viewResources {
	ouLayer("outlayer","outlayer.fxml"),
	rankList("rankList","RankListView.fxml"),
	playerInfor("playerInfor","PlayerInfor.fxml");
	
	private String name;
	private String resource;
	
	private viewResources(String name, String resource){
		this.name=name;
		this.resource=resource;
	}

	public String getName() {
		return name;
	}

	public String getResource() {
		return resource;
	}
}
