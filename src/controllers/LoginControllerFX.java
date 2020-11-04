package controllers;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Person;
import models.Persondao;


/**
 * 
 * Controller which manages operations and event on the Login view
 *
 */
/** Controls the main application screen */
public class LoginControllerFX {

	@FXML
    private AnchorPane rootAnchor;
	@FXML
	private Button buttonlogin;
	@FXML
	private Label lbladmin;
	@FXML
	private TextField txtusername;
	@FXML
	private TextField txtpassword;

	public void login(ActionEvent event) throws IOException {
		String username = txtusername.getText();
		String password = txtpassword.getText();
		if (username != "" && password != "") {
			Persondao userdao = new Persondao();
			Person user = userdao.getByName(username);
			UserSession.getInstance(user);
			if (user != null && user.getUpassword().equals(password)) {
				if (user.getIsadmin()) {
					lbladmin.setText("Login Success");
					Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
					
					rootAnchor.getChildren().setAll(root);
					
				}else if (!user.getIsadmin()) { 
					lbladmin.setText("Login Success");
					Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("User.fxml"));
					
					rootAnchor.getChildren().setAll(root);
					
				}
//					else
//				
//					showAlert(AlertType.ERROR, "Login Failed", "Unauthorized access!");
			} else
				showAlert(AlertType.ERROR, "Login Failed", "Credentials are incorrect!");

		} else
			showAlert(AlertType.ERROR, "Login Failed", "Credentials are required!");
	}

	public void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}
