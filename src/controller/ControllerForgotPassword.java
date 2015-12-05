package controller;


import application.ChangeFxml;
import application.Model;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ControllerForgotPassword  {

	@FXML
	private TextField recoveryUserName;
	@FXML
	private Label hint;
	@FXML
	private Image imageReg;

	/**
	 * Close Menu Option
	 * 
	 * @param ae
	 */
	@FXML
	public void closeProject(ActionEvent ae) {
		MyAlertDialoge.showAlertBox();
	}

	/**
	 * Menu option About me
	 * 
	 * @param ae
	 */
	@FXML
	public void aboutMe(ActionEvent ae) {
		MyAlertDialoge.aboutMe();
	}

	/**
	 * back button
	 * 
	 * @param event
	 */
	@FXML
	public void goBack(Event event) {
		new ChangeFxml().swap("/design/First.fxml");
	}

	/**
	 * Sets Password on Hint field
	 * 
	 * @param actionEvent
	 */
	@FXML
	public void recover(ActionEvent actionEvent) {
		String recoveryText = recoveryUserName.getText().trim();
		boolean b = new Model().checkUserName(recoveryText);
		if (!b) {
			String s = new Model().getPassword(recoveryText);
			hint.setText(s);
		} else {
			hint.setText("This username doesn't exists!");
		}
	}

}
