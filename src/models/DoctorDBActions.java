package models;

import java.sql.SQLException;

public interface DoctorDBActions {

	// public User getUser(String name);
		// public void insert(Object obj);
		 public void insert(Doctor obj) throws SQLException;
			public void update(Doctor obj);
			public void delete(Doctor obj);
			//public Object Find (Object obj);
		    //public List<T> findAll(Object obj);
}
