package controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MyAlertDialoge {

	public static void showAlertBox() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are You Sure?");
		alert.setContentText("You Dont Want to learn more?");
		alert.setTitle("Is it a GOOD BYE?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		}
	}
	
	public static void aboutMe(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Its About Me");
		alert.setHeaderText("Its JavaFx Tutorial");
		alert.setContentText("Made awesome javafx tutorial! \n Its really awesome.");

		alert.showAndWait();
	}
	public static void Success(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("All Changes Have Being saved");
		alert.setHeaderText("Its Saved!");
		alert.showAndWait();
	}
	public static void Warning(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Something Went Wrong");
		alert.setHeaderText("Its not Saved!");
		alert.showAndWait();
	}
}
