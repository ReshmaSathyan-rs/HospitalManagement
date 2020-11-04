package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Patient;
import models.Patientdao;
import models.Person;

/**
 * 
 * Controller which manages operations and event on the ViewPatient partial page
 *
 */
public class UserViewPatientControllerFX implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Patient> patientTable;

	@FXML
	private TableColumn<Patient, String> idCol;

	@FXML
	private TableColumn<Patient, String> nameCol;

	@FXML
	private TableColumn<Patient, String> addressCol;

	@FXML
	private TableColumn<Patient, String> phoneCol;

	@FXML
	private TableColumn<Patient, String> emailCol;

	@FXML
	private TableColumn<Patient, String> personidCol;

	@FXML
	private TextField Patient_Id;

	@FXML
	private TextField Patient_Name;

	@FXML
	private TextField Patient_Address;

	@FXML
	private TextField Patient_Phone;

	@FXML
	private TextField Patient_Email;

	@FXML
	private TextField Person_id;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;
	@FXML
	private AnchorPane editPatientPane;

	ObservableList<Patient> patients = FXCollections.observableArrayList();

	public UserViewPatientControllerFX() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient_id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient_name"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient_add"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient_phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient_email"));
//		personidCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("uid"));

		// add delete button on row
		//TableColumn<Patient, Boolean> col_action = new TableColumn<Patient, Boolean>("Action");

//		patientTable.getColumns().add(col_action);
//
//		col_action.setCellValueFactory(
//				new Callback<TableColumn.CellDataFeatures<Patient, Boolean>, ObservableValue<Boolean>>() {
//
//					@Override
//					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Patient, Boolean> p) {
//						return new SimpleBooleanProperty(p.getValue() != null);
//					}
//				});

//		// Adding the Button to the cell
//		col_action.setCellFactory(new Callback<TableColumn<Patient, Boolean>, TableCell<Patient, Boolean>>() {
//
//			@Override
//			public TableCell<Patient, Boolean> call(TableColumn<Patient, Boolean> p) {
//				return new ButtonCell();
//			}
//
//		});

		// bind data of the database to the table
		bindData();
		// edit row on click
		editRow();
	}

	// edit row of the table on double click
	private void editRow() {

		patientTable.toFront();
		patientTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				Patient_Id.setText(String.valueOf(nv.getPatient_id()));
				Patient_Name.setText(nv.getPatient_name());
				Patient_Address.setText(nv.getPatient_add());
				Patient_Phone.setText(nv.getPatient_phone());
				Patient_Email.setText(nv.getPatient_email());
//				Person_id.setText(String.valueOf((nv.getUid())));

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Patientdao patdao = new Patientdao();
				Patient item = patientTable.getSelectionModel().getSelectedItem();
				item.setPatient_id(Integer.parseInt(Patient_Id.getText()));
				item.setPatient_name(Patient_Name.getText());
				item.setPatient_add(Patient_Address.getText());
				item.setPatient_phone(Patient_Phone.getText());
				item.setPatient_email(Patient_Email.getText());
//				item.setUid(Integer.parseInt(Person_id.getText()));
				try {
					patdao.userupdate(item);
				patientTable.refresh();
				showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
						"Patient id " + item.getPatient_id() + " is updated in the database");
				}
				catch (Exception e) {
					showAlert(AlertType.ERROR, "Patient",
							"Error occurred while updating entry, message:" + e.getMessage());
				}
				patientTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				patientTable.toFront();
			}
		});

		patientTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					editPatientPane.toFront();
				}
			}
		});
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Patientdao Patientdao = new Patientdao();
		Person user = UserSession.getInstance().getUser();
		List<Patient> resultSet = Patientdao.usergetRecords(user);
		for (Patient doc : resultSet) {
			patients.add(doc);
		}

		patientTable.setItems(patients);

	}

//	// Define the button cell
//	class ButtonCell extends TableCell<Patient, Boolean> {
//		final Button cellButton = new Button("Delete");
//
//		ButtonCell() {
//
//			// Action when the button is pressed
//			cellButton.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent t) {
//					// get Selected Item
//					Patient currentPerson = (Patient) ButtonCell.this.getTableView().getItems()
//							.get(ButtonCell.this.getIndex());
//					// remove selected item from the table list
//					patients.remove(currentPerson);
//					Patient pat = new Patient();
//					Patientdao patdao = new Patientdao();
//
//					pat.setPatient_id(currentPerson.getPatient_id());
//					try {
//						//patdao.delete(pat);
//					patientTable.refresh();
//					showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
//							"Patient id " + currentPerson.getPatient_id() + " is deleted from  the database");
//					}
//					catch (Exception e) {
//						showAlert(AlertType.ERROR, "Patient",
//								"Error occurred while deleting entry, message:" + e.getMessage());
//					}
//
//				}
//			});
//
//			cellButton.getStyleClass().add("danger");
//		}
//
//		// Display button if the row is not empty
//		@Override
//		protected void updateItem(Boolean t, boolean empty) {
//			super.updateItem(t, empty);
//			if (!empty) {
//				setGraphic(cellButton);
//			}
//		}
//
//	}

	public void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();

	}

}