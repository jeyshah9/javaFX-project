package controller;

import application.ChangeFxml;
import application.Model;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerRegistration {

	@FXML
	private Button btnGoBack;
	@FXML
	private TextField userNameRegistration;
	@FXML
	private PasswordField password, rePassword;

	@FXML
	private Label errorUserName, errorPassword;

	@FXML
	private Pane pane;

	/**
	 * Event for Registration btn and validation also
	 * 
	 * @param actionEvent
	 */
	@FXML
	public void registrationBtnClicked(ActionEvent actionEvent) {
		String passwordText, rePasswordText, userNameText;
		userNameText = userNameRegistration.getText();
		passwordText = password.getText();
		rePasswordText = rePassword.getText();
		errorPassword.setText("Atleast 6 Character");
		errorUserName.setText("Please Type Email");
		boolean checkUserNameCheck = new Model().checkUserName(userNameText);
		if (checkUserNameCheck) {
			if (passwordText.length() > 5) {
				if (passwordText.equals(rePasswordText)) {
					// System.out.println(+">>"++">>"+rePasswordText);
					new Model().registrationUser(userNameText, passwordText);
				} else {
					errorPassword.setText("Didn't Match Password");
					errorUserName.setText("Looks Good");
				}
			}

		} else {
			errorUserName.setText("Already Exists");
		}

	}

	/**
	 * No Editing after this point
	 * 
	 * @param ae
	 */
	@FXML
	public void goBack(Event ae) {
		pane.getChildren().clear();
		new ChangeFxml().swap("/design/First.fxml");

	}

	@FXML
	public void close(ActionEvent actionEvent) {
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
}
