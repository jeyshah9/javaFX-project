package controller;

import java.net.URL;
import java.util.ResourceBundle;


import application.ChangeFxml;
import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerHomeAdmin implements Initializable {
	@FXML
	private Label headerLabelAdmin;
	@FXML
	private TextField addName, editText;
	@FXML
	private TextArea addDescription, editDescription;
	@FXML
	private ComboBox<String> choiceList;
	@FXML
	TableView<StudentData> tbl;
	@FXML
	TableColumn<String, String> tblNumber,tblName,tblPassword,tblDate,tblReg;

	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		choiceList.getItems().addAll(Model.values());
		
		tblNumber.setCellValueFactory(new PropertyValueFactory<String,String>("Number"));
		tblName.setCellValueFactory(new PropertyValueFactory<String,String>("Name"));
		tblPassword.setCellValueFactory(new PropertyValueFactory<String,String>("Password"));
		tblDate.setCellValueFactory(new PropertyValueFactory<String,String>("Last"));
		tblReg.setCellValueFactory(new PropertyValueFactory<String,String>("Registered"));
		
		tbl.setItems(getProduct());
		

	}
	
	 //Get all of the products
    public ObservableList<StudentData> getProduct(){
        ObservableList<StudentData> products = FXCollections.observableArrayList();
        for(int i =1; i<Model.getUserCount() ;i++){
        	
        	products.add(new StudentData(i));	
        	
        }
        
        return products;
    }


	/**
	 * System Close
	 * 
	 * @param ae
	 */
	@FXML
	public void closeSystem(ActionEvent ae) {
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
	 * SignOff Event
	 * 
	 * @param actionEvent
	 */
	@FXML
	public void signOff(ActionEvent actionEvent) {
		new ChangeFxml().swap("/design/First.fxml");

	}

	/**
	 * Save Files
	 * 
	 * @param ae
	 */

	@SuppressWarnings("unchecked")
	@FXML
	public void saveIt(ActionEvent ae) {
		if (!addName.getText().equals("") || !addName.getText().equals(null) || !addDescription.getText().equals("")
				|| !addDescription.getText().equals(null)) {
			boolean b = Model.insertData(addName.getText().toString(), addDescription.getText().toString());

			if (b) {

				choiceList.getItems().clear();
				choiceList.getItems().addAll(Model.values());
				addName.setText("");
				addDescription.setText("");
				MyAlertDialoge.Success();
			} else {
				MyAlertDialoge.Warning();
			}
		} else if (!editText.getText().equals("") || !editText.getText().equals(null)
				|| !editDescription.getText().equals("") || !editDescription.getText().equals(null)) {
			updateIt(ae);
		} else {
			MyAlertDialoge.Warning();
		}

	}

	/**
	 * It gets the value from data table
	 * 
	 * @param actionEvent
	 */
	@FXML
	public void changeChoiceBox(ActionEvent actionEvent) {
		try {
			String ChoiceValue = choiceList.getSelectionModel().getSelectedItem().toString();
			editText.setText(ChoiceValue);
			String s = Model.allValues(ChoiceValue);
			editDescription.setText(s);
		} catch (Exception e) {

		}
	}

	/**
	 * It Updates The Value in first screen.! edit Box
	 * @param actionEvent
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void updateIt(ActionEvent actionEvent) {
		if (!editText.getText().equals("") || !editText.getText().equals(null) || !editDescription.getText().equals("")
				|| !editDescription.getText().equals(null)) {
			String ChoiceValue = choiceList.getSelectionModel().getSelectedItem().toString();
			String textFeild = editText.getText().toString();
			String textArea = editDescription.getText().toString();
			boolean b = Model.updateValues(ChoiceValue, textFeild, textArea);
			if (!b) {
				MyAlertDialoge.Warning();
			} else {
				System.out.println("Success");
				choiceList.getItems().clear();
				choiceList.getItems().addAll(Model.values());
				editText.setText("");
				editDescription.setText("");
				MyAlertDialoge.Success();

			}

		}
	}

	
}
