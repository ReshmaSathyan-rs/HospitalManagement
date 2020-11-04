package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class PersonDBOperations implements PersonDBActions {

	
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public PersonDBOperations() { // create db object instance
		conn = new DBConnect();
	}
	
	public void insert(Patient pat) {
		// TODO Auto-generated method stub
		String sql = null;
		try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String value1 = pat.getPatient_name();
		 String value2 = pat.getPatient_add();
		 String value3 = pat.getPatient_phone();
		 String value4 = pat.getPatient_email();
		 int value5 = pat.getUid();

		// Include all object data to the database table
		
			sql = "INSERT INTO Patient(patient_name,patient_add, patient_phone, patient_email,uid) " +
			"VALUES ('"+value1+"','"+value2+"','"+value3+"','"+value4+"','"+value5+"' )";
			 
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	

	public Person findByName(String name) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement statement = conn.connect().prepareStatement("select * from user where uname = ?");    
			statement.setString(1, name);    
			 rs = statement.executeQuery();
			
			 if(rs.next())
	            {
	                Person user = new Person();
	                user.setUid( rs.getInt("uid") );
	                user.setUname( rs.getString("uname") );
	                user.setUpassword( rs.getString("upassword") );
	                user.setIsadmin( rs.getBoolean("isadmin") );
	                return user;
	            }
			
			conn.connect().close();// close db connection
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
		
	}
	

//	public List findAll(Class<User> class1) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	public List findAll(Class<Patient> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person insertuser(Person person) {
		// TODO Auto-generated method stub
		String sql = null;
		try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String value1 = person.getUname();
		 String value2 = person.getUpassword();
		 int value3;
		  
		 if (person.getIsadmin() == true) {
			 value3 = 1;
		 }else {
			 value3 = 0;
		 }

		// Include all object data to the database table
		
			sql = "INSERT INTO User(uname,upassword, isadmin) " +
			"VALUES ('"+value1+"','"+value2+"','"+value3+"')";
			 
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return person;
	}

}
