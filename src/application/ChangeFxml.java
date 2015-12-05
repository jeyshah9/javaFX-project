package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ChangeFxml {

	
	public void swap(String s) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(s));
			Scene scene = new Scene(root);
			Main.window.setScene(scene);
			Main.window.show();
			Main.window.setTitle("JS793");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
