package gmbh.conteco;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	static public void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(TimeSheetScene.getScene());
		primaryStage.setTitle("Zeiterfassung");
		primaryStage.show();
	}
}