package models;

import java.sql.SQLException;

public interface AppointmentDBActions {
	// public User getUser(String name);
		// public void insert(Object obj);
		 public void insert(Appointment obj) throws SQLException;
			public void update(Appointment obj);
			public void delete(Appointment obj);
			//public Object Find (Object obj);
		    //public List<T> findAll(Object obj);

}
