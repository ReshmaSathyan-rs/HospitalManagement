package models;

import java.sql.SQLException;

public interface PatientDBActions {
	
	// public User getUser(String name);
	// public void insert(Object obj);
	 public void insert(Patient obj) throws SQLException;
		public void update(Patient obj);
		public void delete(Patient obj);
		//public Object Find (Object obj);
	    //public List<T> findAll(Object obj);

}
