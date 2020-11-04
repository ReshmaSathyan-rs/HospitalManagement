package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AppointmentDBOperations implements AppointmentDBActions {
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public AppointmentDBOperations() { // create db object instance
		conn = new DBConnect();
	}

	@Override
	public void insert(Appointment obj) throws SQLException {
		// TODO Auto-generated method stub
		String sql = null;
		try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int value1 = obj.getDoctor_id();
		int value2 = obj.getPatient_id();
		String value3 = obj.getDate();
		String value4 = obj.getTime();

		// Include all object data to the database table

		sql = "INSERT INTO Appointment(doctor_id,patient_id,date,time) " + "VALUES (" + value1 + "," + value2 + ",'"
				+ value3 + "','" + value4 + "')";

		try {
			stmt.executeUpdate(sql);
			conn.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void update(Appointment obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Appointment obj) {
		// TODO Auto-generated method stub

	}

	public void insert(Patient pat) {
		// TODO Auto-generated method stub

	}

	public void update(Patient pat) {
		// TODO Auto-generated method stub

	}

	public void delete(Patient pat) {
		// TODO Auto-generated method stub

	}

	public Patient find(Class<Patient> class1, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll(Class<AppointmentWithDoctorAndPatient> class1) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<AppointmentWithDoctorAndPatient> list = new ArrayList<AppointmentWithDoctorAndPatient>( );
		String sql = null;
		try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Include all object data to the database table

		sql = "SELECT date, time, doctor.doctor_name as doctor_name, doctor.doctor_specialization, patient.patient_id as patient_id ,appointment.app_id as appointment_id from appointment LEFT JOIN doctor on appointment.doctor_id = doctor.doctor_id LEFT JOIN patient on patient.patient_id = appointment.patient_id;";

		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AppointmentWithDoctorAndPatient app = new AppointmentWithDoctorAndPatient();
				app.setId( rs.getInt("appointment_id") );
				app.setDoctorSpecialization( rs.getString("doctor_specialization") );
				app.setDoctorName( rs.getString("doctor_name") );
				app.setPatientId( rs.getInt("patient_id") );
				app.setDate( rs.getString("date") );
				app.setTime( rs.getString("time") );
                list.add(app);	
				
			}
			conn.connect().close();
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public List<AppointmentWithDoctorAndPatient> userfindAll(Person user) throws SQLException {
		// TODO Auto-generated method stub
		List<AppointmentWithDoctorAndPatient> list = new ArrayList<AppointmentWithDoctorAndPatient>( );
		Patientdao pat = new Patientdao();
		List<Patient> patient = pat.usergetRecords(user);
		int patientId = patient.get(0).getPatient_id();
		
		String sql = null;
		try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Include all object data to the database table

		sql = "SELECT date, time, doctor.doctor_name as doctor_name, doctor.doctor_specialization, patient.patient_id as patient_id ,appointment.app_id as appointment_id from appointment LEFT JOIN doctor on appointment.doctor_id = doctor.doctor_id LEFT JOIN patient on patient.patient_id = appointment.patient_id where appointment.patient_id="+patientId;

		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AppointmentWithDoctorAndPatient app = new AppointmentWithDoctorAndPatient();
				app.setId( rs.getInt("appointment_id") );
				app.setDoctorSpecialization( rs.getString("doctor_specialization") );
				app.setDoctorName( rs.getString("doctor_name") );
				app.setPatientId( rs.getInt("patient_id") );
				app.setDate( rs.getString("date") );
				app.setTime( rs.getString("time") );
                list.add(app);	
				
			}
			conn.connect().close();
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void userupdate(Patient item) {
		// TODO Auto-generated method stub

	}

	public void update(AppointmentWithDoctorAndPatient item) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement ps = conn.connect().prepareStatement(
				      "UPDATE Appointment SET date = ?, time = ? WHERE app_id = ?");

				    String date = item.getDate();
					// set the preparedstatement parameters
				    ps.setString(1,date);
				    String time = item.getTime();
					ps.setString(2,time);
				   
				   
				    int app_ido = item.getId();
					ps.setInt(3,app_ido);
				    
				    // call executeUpdate to execute our sql update statement
				    ps.executeUpdate();
				    ps.close();
				    conn.connect().close();
			} catch (SQLException se) {// Handle errors for JDBC
				se.printStackTrace();
			}
	}

	public void delete(AppointmentWithDoctorAndPatient pat) {
		// TODO Auto-generated method stub
try {
			
			PreparedStatement ps = conn.connect().prepareStatement(
				      "delete from Appointment where app_id = ?");

				    int app_id = pat.getId();
					// set the preparedstatement parameters
				    ps.setInt(1,app_id);
				    
				    // call executeUpdate to execute our sql update statement
				    ps.executeUpdate();
				    ps.close();
				    conn.connect().close();
			} catch (SQLException se) {// Handle errors for JDBC
				se.printStackTrace();
			}
	}

}
