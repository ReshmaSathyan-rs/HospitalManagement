package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DoctorDBOperations implements DoctorDBActions {

	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public DoctorDBOperations() { // create db object instance
		conn = new DBConnect();
	}

	@Override
	public void insert(Doctor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Doctor obj) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void delete(Doctor obj) {
		// TODO Auto-generated method stub

	}

	public Doctor find(Class<Doctor> class1, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll(Class<Doctor> class1) throws SQLException {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		// TODO Auto-generated method stub

		PreparedStatement statement = conn.connect().prepareStatement("select * from Doctor");

		rs = statement.executeQuery();
		ArrayList<Doctor> list = new ArrayList<Doctor>();

		while (rs.next()) {
			Doctor doc = new Doctor();
			doc.setDoctor_id(rs.getInt("doctor_id"));
			doc.setDoctor_name(rs.getString("doctor_name"));
			doc.setDoctor_specialization(rs.getString("doctor_specialization"));

			list.add(doc);

		}
		conn.connect().close();
		return list;
		// close db connection

	}

	public List<Doctor> userfindAll(Doctor doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public void userupdate(Doctor item) {
		// TODO Auto-generated method stub

	}

}
