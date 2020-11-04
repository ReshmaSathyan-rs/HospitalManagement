package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import models.Appointment;
import models.Appointmentdao;
import models.Doctor;
import models.Doctordao;
import models.Patient;
import models.Patientdao;

/**
 * 
 * Controller which manages operations and event on the Add Patient view
 *
 */
public class AdminAddAppointmentControllerFX implements Initializable {

	@FXML
	private ComboBox doctor_spec;

	@FXML
	private ComboBox<Doctor> doctor_name;

	@FXML
	private DatePicker appointment_date;

	@FXML
	private ComboBox<String> appointment_time;

	@FXML
	private ComboBox<Patient> patient_id;

	@FXML
	private JFXButton addBtn;

	ObservableList<Doctor> doctors = FXCollections.observableArrayList();
	ObservableList<String> doctorSpecializations = FXCollections.observableArrayList();
	ObservableList<Doctor> doctorsFiltered = FXCollections.observableArrayList();
	ObservableList<Patient> patients = FXCollections.observableArrayList();
	ObservableList<String> time = FXCollections.observableArrayList("10:00 AM", "10:30 AM", "11:00 AM");

	@Override
	public void initialize(URL location, ResourceBundle arg1) {

		if (!this.isAdmin()) {
			patient_id.setVisible(false);
		}
		bindData();
		attachEventHandlers();

	}

	private boolean isAdmin() {
		return UserSession.isAdmin();
	}

	private void bindData() {
		// DaoModel model = new DaoModel();
		appointment_time.setItems(time);
		loadDoctors();
		if (this.isAdmin()) {
			loadPatient();
		}

	}

	private void loadPatient() {

		// DaoModel model = new DaoModel();
		Patientdao Patientdao = new Patientdao();

		List<Patient> resultSet = Patientdao.getRecords(new Patient());
		for (Patient pat : resultSet) {
			patients.add(pat);
		}
		patient_id.setItems(patients);
		patient_id.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {

			@Override
			public ListCell<Patient> call(ListView<Patient> p) {

				final ListCell<Patient> cell = new ListCell<Patient>() {

					@Override
					protected void updateItem(Patient t, boolean bln) {
						super.updateItem(t, bln);

						if (t != null) {
							setText(t.getPatient_name());
						} else {
							setText(null);
						}
					}

				};

				return cell;
			}
		});

	}

	private void loadDoctors() {
		Doctordao doc = new Doctordao();

		List<Doctor> resultSet;
		try {
			resultSet = doc.getRecords(new Doctor());
			for (Doctor doctor : resultSet) {
				String specialization = doctor.getDoctor_specialization();
				doctors.add(doctor);
				if (!doctorSpecializations.contains(specialization)) {
					doctorSpecializations.add(doctor.getDoctor_specialization());
				}
			}
			doctor_spec.setItems(doctorSpecializations);
			doctor_name.setItems(doctorsFiltered);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doctor_name.setCellFactory(new Callback<ListView<Doctor>, ListCell<Doctor>>() {

			@Override
			public ListCell<Doctor> call(ListView<Doctor> p) {

				final ListCell<Doctor> cell = new ListCell<Doctor>() {

					@Override
					protected void updateItem(Doctor t, boolean bln) {
						super.updateItem(t, bln);

						if (t != null) {
							setText(t.getDoctor_name());
						} else {
							setText(null);
						}
					}

				};

				return cell;
			}
		});

	}

	private void attachEventHandlers() {
		EventHandler<ActionEvent> onSpecChange = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				doctorsFiltered.clear();
				for (Doctor doctor : doctors) {

					String selectedSpecialization = (String) doctor_spec.getValue();
					// System.out.println(selectedSpecialization +
					// ","+doctor.getDoctor_specialization());
					if (doctor.getDoctor_specialization().equals(selectedSpecialization)) {
						// System.out.println("Added");
						doctorsFiltered.add(doctor);
					}
				}

			}
		};

		// Set on action
		doctor_spec.setOnAction(onSpecChange);

	}

	@FXML
	void addappointment(ActionEvent event) {

		if (doctor_spec.getPromptText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select doctor specialization");
			return;
		}
		if (doctor_name.getPromptText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select doctor name");
			return;
		}
		if (appointment_date.getPromptText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select appointment date");
			return;
		}

		if (appointment_time.getPromptText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select appointment time");
			return;
		}

		if (patient_id.getPromptText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select patient id");
			return;
		}

		Appointment appointment = new Appointment();
		Appointmentdao appdao = new Appointmentdao();

		appointment.setDoctor_id(doctor_name.getValue().getDoctor_id());

		if (this.isAdmin()) {
			appointment.setPatient_id(patient_id.getValue().getPatient_id());
		} else {
			UserSession usr = UserSession.getInstance();
			Patientdao pat = new Patientdao();

			List<Patient> patient = pat.usergetRecords(usr.getUser());
			appointment.setPatient_id(patient.get(0).getPatient_id());
		}

		Date date = java.util.Date.from(appointment_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		appointment.setDate(formattedDate);
		appointment.setTime(appointment_time.getValue());

		try {

			appdao.insert(appointment);
			showAlert(AlertType.INFORMATION, "Book Appointment", "Booking Appointment Sucessful");
		} catch (Exception e) {
			showAlert(AlertType.ERROR, "Book Appointment",
					"Error while booking appointment, message:" + e.getMessage());
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}
