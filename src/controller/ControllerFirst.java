package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ChangeFxml;
import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerFirst implements Initializable {

	@FXML
	private String DropDownvalue;
	@FXML
	private ChoiceBox<String> dropDown;
	@FXML
	private Button login;
	@FXML
	private AnchorPane FirstAnchorPane, c;
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	

	// Initialize values of dropdown
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		dropDown.getItems().add("Admin");
		dropDown.getItems().add("Client");
		
		
	}

	/**
	 * Drop down event gets values of drop down
	 * 
	 * @param actionEvent
	 */

	public void changeAdmin(ActionEvent actionEvent) {

		DropDownvalue = dropDown.getSelectionModel().getSelectedItem();

	}

	/**
	 * Login button event, 1) IF:- checks if nothing is null 2) IF:-
	 * Admin/Client, checks valid login 3) IF:- Update Date access and process
	 * to home page
	 * 
	 * @param ae
	 */
	@FXML
	public void loginBtn(ActionEvent ae) {
		if (DropDownvalue != null && userName != null && password != null) {

			String userNameText = userName.getText().trim();
			String passwordText = password.getText().trim();

			if (DropDownvalue.equals("Admin")) {

				boolean check = new Model().getLogin("a", userNameText, passwordText);
				if (check) {
					boolean b = new Model().updateDateLogin("a", userNameText, passwordText);
					if (b) {
						new ChangeFxml().swap("/design/HomeAdmin.fxml");
					} else {
						ResetBtnClick(ae);
						userName.setPromptText("Invalid! Email");
						password.setPromptText("Invalid! Password");
					}

				} else {
					ResetBtnClick(ae);
					userName.setPromptText("Invalid! Email");
					password.setPromptText("Invalid! Password");
				}

			} else {
				boolean check = new Model().getLogin("u", userNameText, passwordText);
				if (check) {
					boolean b = new Model().updateDateLogin("u", userNameText, passwordText);
					if (b) {
						new ChangeFxml().swap("/design/Home.fxml");
					} else {
						ResetBtnClick(ae);
						userName.setPromptText("Something went wrong!");
						password.setPromptText("Invalid!");
					}

				} else {
					ResetBtnClick(ae);
					userName.setPromptText("Invalid!");
					password.setPromptText("Invalid!");
				}
			}

		}
	}

	/**
	 * Register Btn Event Sends to Registration screen
	 * 
	 * @param ae
	 * @throws IOException
	 */

	@FXML
	public void RegisterBtnClick(ActionEvent ae) throws IOException {

		new ChangeFxml().swap("/design/Registration.fxml");

	}

	/**
	 *  Reset Btn Event
	 *  Reset all the field
	 * @param ae
	 */
	@FXML
	public void ResetBtnClick(ActionEvent ae) {
		password.setText("");
		userName.setText("");
		dropDown.getSelectionModel().clearSelection();
	}
	/**
	 * Thats the menu bar event About !!
	 * @param ae
	 */

	@FXML
	public void aboutMe(ActionEvent ae) {
		MyAlertDialoge.aboutMe();
	}

	/**
	 * Sends to forgetPassword.fxml screen
	 * @param actionEvent
	 */
	@FXML
	public void forgotPassword(ActionEvent actionEvent) {
		new ChangeFxml().swap("/design/ForgotPassword.fxml");
	}
	
	/**
	 * Closes the system 
	 * @param actionEvent
	 */
	@FXML
	public void closeSystem(ActionEvent actionEvent){
		MyAlertDialoge.showAlertBox();
	}
}