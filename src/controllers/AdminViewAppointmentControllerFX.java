package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.Appointment;
import models.AppointmentWithDoctorAndPatient;
import models.Appointmentdao;
import models.Patient;
import models.Patientdao;

/**
 * 
 * Controller which manages operations and event on the ViewPatient partial page
 *
 */
public class AdminViewAppointmentControllerFX implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<AppointmentWithDoctorAndPatient> appointmentTable;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> apidCol;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> doctorSpec;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> doctorName;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> date;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> time;

	@FXML
	private TableColumn<AppointmentWithDoctorAndPatient, String> patientId;

	@FXML
	private TextField Appointment_Id;

	@FXML
	private TextField Doctor_Spec;

	@FXML
	private TextField Doctor_name;

	@FXML
	private DatePicker Date;

	@FXML
	private ComboBox<String> Time;

	@FXML
	private TextField Patient_id;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;
	@FXML
	private AnchorPane editAppointmentPane;

	ObservableList<AppointmentWithDoctorAndPatient> appointments = FXCollections.observableArrayList();
	ObservableList<String> timeOptions = FXCollections.observableArrayList("10:00 AM", "10:30 AM", "11:00 AM");

	public AdminViewAppointmentControllerFX() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		apidCol.setCellValueFactory(new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("id"));
		doctorSpec.setCellValueFactory(
				new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("doctorSpecialization"));
		doctorName.setCellValueFactory(new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("doctorName"));
		date.setCellValueFactory(new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("time"));
		patientId.setCellValueFactory(new PropertyValueFactory<AppointmentWithDoctorAndPatient, String>("patientId"));
		//if (UserSession.isAdmin()) {
		// add delete button on row
		TableColumn<AppointmentWithDoctorAndPatient, Boolean> col_action = new TableColumn<AppointmentWithDoctorAndPatient, Boolean>(
				"Action");

		appointmentTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<AppointmentWithDoctorAndPatient, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(
							TableColumn.CellDataFeatures<AppointmentWithDoctorAndPatient, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(
				new Callback<TableColumn<AppointmentWithDoctorAndPatient, Boolean>, TableCell<AppointmentWithDoctorAndPatient, Boolean>>() {

					@Override
					public TableCell<AppointmentWithDoctorAndPatient, Boolean> call(
							TableColumn<AppointmentWithDoctorAndPatient, Boolean> p) {
						return new ButtonCell();
					}

				});
		//}
		// bind data of the database to the table
		bindData();
		// edit row on click

		if (UserSession.isAdmin()) {
			editRow();
		} else {
			appointmentTable.toFront();
		}
		//		 Date.setConverter(new StringConverter<LocalDate>() {
		//		     String pattern = "yyyy-MM-dd";
		//		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		//
		//		     {
		//		         Date.setPromptText(pattern.toLowerCase());
		//		     }
		//
		//		     @Override public String toString(LocalDate date) {
		//		         if (date != null) {
		//		             return dateFormatter.format(date);
		//		         } else {
		//		             return "";
		//		         }
		//		     }
		//
		//		     @Override public LocalDate fromString(String string) {
		//		         if (string != null && !string.isEmpty()) {
		//		             return LocalDate.parse(string, dateFormatter);
		//		         } else {
		//		             return null;
		//		         }
		//		     }
		//		 });

	}

	// edit row of the table on double click
	private void editRow() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		appointmentTable.toFront();
		appointmentTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				Appointment_Id.setText(String.valueOf(nv.getId()));
				Doctor_Spec.setText(nv.getDoctorSpecialization());
				Doctor_name.setText(nv.getDoctorName());
				Date.setValue(LocalDate.parse(nv.getDate(), dateFormatter));
				Time.getSelectionModel().select(nv.getTime());

				Patient_id.setText(String.valueOf((nv.getPatientId())));

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Appointmentdao appdao = new Appointmentdao();
				AppointmentWithDoctorAndPatient item = appointmentTable.getSelectionModel().getSelectedItem();
				item.setId(Integer.parseInt(Appointment_Id.getText()));
				item.setDoctorSpecialization(Doctor_Spec.getText());
				item.setDoctorName(Doctor_name.getText());
				item.setDate(dateFormatter.format(Date.getValue()));
				item.setTime(Time.getValue());
				item.setPatientId(Integer.parseInt(Patient_id.getText()));
				try {
					appdao.update(item);
					appointmentTable.refresh();
					showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
							"Appointment id " + item.getId() + " is updated in the database");
				} catch (Exception e) {
					showAlert(AlertType.ERROR, "Appointment",
							"Error occurred while updating entry, message:" + e.getMessage());
				}
				appointmentTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				appointmentTable.toFront();
			}
		});

		appointmentTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					editAppointmentPane.toFront();
				}
			}
		});
	}

	private void bindData() {
		Time.setItems(timeOptions);
		// DaoModel model = new DaoModel();
		Appointmentdao appointmentdao = new Appointmentdao();

		List<AppointmentWithDoctorAndPatient> resultSet;
		try {
			if (UserSession.isAdmin()) {
				resultSet = appointmentdao.getRecords();
			} else {
				UserSession usr = UserSession.getInstance();

				resultSet = appointmentdao.usergetRecords(usr.getUser());
			}
			for (AppointmentWithDoctorAndPatient doc : resultSet) {
				appointments.add(doc);
			}

			appointmentTable.setItems(appointments);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Define the button cell
	class ButtonCell extends TableCell<AppointmentWithDoctorAndPatient, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					AppointmentWithDoctorAndPatient currentPerson = (AppointmentWithDoctorAndPatient) ButtonCell.this
							.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					appointments.remove(currentPerson);
					AppointmentWithDoctorAndPatient pat = new AppointmentWithDoctorAndPatient();
					Appointmentdao patdao = new Appointmentdao();

					pat.setId(currentPerson.getId());
					try {
						patdao.delete(pat);
						appointmentTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
								"Appointment id " + currentPerson.getId() + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.ERROR, "Appointment",
								"Error occurred while deleting entry, message:" + e.getMessage());
					}

				}
			});

			cellButton.getStyleClass().add("danger");
		}

		// Display button if the row is not empty
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}

	}

	public void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();

	}

}