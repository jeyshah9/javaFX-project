package application;


import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

@SuppressWarnings("rawtypes")
public class Main extends Application implements EventHandler {
	public static Stage window;	 

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/design/First.fxml"));
			
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("JS793");
			window.setResizable(false);
			window.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}

}
