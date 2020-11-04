package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import models.Patient;
import models.Patientdao;

/**
 * 
 * Controller which manages operations and event on the Add Patient view
 *
 */
public class AdminAddPatientControllerFX implements Initializable {

	@FXML
	private TextArea patient_name;

	@FXML
	private TextArea patient_add;

	@FXML
	private TextArea patient_phone;

	@FXML
	private TextArea patient_email;

	@FXML
	private TextArea uid;

	@FXML
	private JFXButton addBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void addPatient(ActionEvent event) {

		if (patient_name.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter patient name");
			return;
		}
		if (patient_add.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter patient address");
			return;
		}
		if (patient_phone.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter patient phone");
			return;
		}
		
		if (!validate_phone(patient_phone.getText())) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid phone number");
			return;
		}

		if (patient_email.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter patient email id");
			return;
		}
		if (!validate_email(patient_email.getText())) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email id");
			return;
		}
		
		
		
		if (uid.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter user id");
			return;
		}

		if (!validate(uid.getText())) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a numeric value for user id");
			return;
		}
		

		Patient patient = new Patient();
		Patientdao patientdao = new Patientdao();

		patient.setPatient_name(patient_name.getText());
		patient.setPatient_add(patient_add.getText());
		patient.setPatient_phone(patient_phone.getText());
		patient.setPatient_email(patient_email.getText());
		patient.setUid(Integer.parseInt(uid.getText()));

		try {
			patientdao.insertData(patient);
			showAlert(AlertType.INFORMATION, "Patient Insert", "Patient successfully inserted");
		} catch (Exception e) {
			showAlert(AlertType.ERROR, "Patient Insert", "Error inserting record, message:" + e.getMessage());
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}
	
	private boolean validate_email(String text) {
		return text.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$");
		 
               
		
	}
	
	private boolean validate_phone(String text) {
		return text.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
	}
}
