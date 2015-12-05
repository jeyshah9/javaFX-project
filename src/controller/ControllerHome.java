package controller;

import java.awt.ScrollPane;
import java.net.URL;
import java.util.ResourceBundle;
import application.ChangeFxml;
import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class ControllerHome implements Initializable {

	@FXML
	private Label headerLabelUser,showLabel;
	@FXML
	private Pagination pagining;
	ScrollPane scrollPane ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		headerLabelUser.setText("Hello," + Model.nameOfUser);
		if(Model.getCount()>0){
			pagining.setPageCount(Model.getCount());

			pagining.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
			showLabel.setVisible(false);
			
		}else{
			pagining.setVisible(false);
			showLabel.setVisible(true);
			showLabel.setText("No Tutorials right now, Plz Check Back");
			
		}
		
	}

	public VBox createPage(int pageIndex) {
		VBox box = new VBox();
		
		box.setPadding(new Insets(10, 50, 50, 50));
		String values[] = Model.getHeader(pageIndex+1);
		Text header = new Text(values[0]);
		
		header.setFont(Font.font("Verdana", FontPosture.ITALIC,24));
		header.setFill(Color.BLACK);
		TextArea containt = new TextArea(values[1]);
		containt.setStyle("-fx-text-fill:red;");
		containt.setPrefRowCount(150);
		containt.setEditable(false);
		Text date = new Text("Date Modified:  "+values[2]+"  ~Admin");
		date.setFill(Color.BLUEVIOLET);
		box.getChildren().add(header);
		box.getChildren().add(containt);
		box.getChildren().add(date);
		return box;
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

}
