package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * Controller which manages operations and event on the Main Page
 *
 */
public class AdminControllerFX implements Initializable {
	@FXML
	private Menu patientTab;

	@FXML
	private MenuItem addPatient;

	@FXML
	private MenuItem ViewPatient;

	@FXML
	private Menu appointmentTab;

	@FXML
	private MenuItem addAppointment;

	@FXML
	private MenuItem viewAppointment;

	


	@FXML
	private AnchorPane dynamicPane;

	@FXML
	void handleAddPatientAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AdminAddPatient.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewPatientAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AdminViewPatient.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleAddAppointmentAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AdminAddAppointment.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewAppointmentAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AdminViewAppointment.fxml"));
		dynamicPane.getChildren().setAll(child);
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}