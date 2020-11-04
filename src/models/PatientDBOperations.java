package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class PatientDBOperations implements PatientDBActions {

	
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public PatientDBOperations() { // create db object instance
		conn = new DBConnect();
	}
	
	public void insert(Patient pat) throws SQLException {
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
					conn.connect().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
			
		
	}

	public void update(Patient pat) {
		// TODO Auto-generated method stub
		try {
		
		PreparedStatement ps = conn.connect().prepareStatement(
			      "UPDATE Patient SET patient_id = ?, patient_name = ?, patient_add = ?, patient_phone = ?,patient_email= ?,uid=? WHERE patient_id = ?");

			    int patient_id = pat.getPatient_id();
				// set the preparedstatement parameters
			    ps.setInt(1,patient_id);
			    String patient_name = pat.getPatient_name();
				ps.setString(2,patient_name);
			    String patient_add = pat.getPatient_add();
				ps.setString(3,patient_add);
			    String patient_phone = pat.getPatient_phone();
				ps.setString(4,patient_phone);
			    String patient_email = pat.getPatient_email();
				ps.setString(5,patient_email);
			    int uid = pat.getUid();
				ps.setInt(6,uid);
			    int patient_ido = pat.getPatient_id();
				ps.setInt(7,patient_ido);
			    
			    // call executeUpdate to execute our sql update statement
			    ps.executeUpdate();
			    ps.close();
			    conn.connect().close();
		} catch (SQLException se) {// Handle errors for JDBC
			se.printStackTrace();
		}
		
	}

	public void delete(Patient pat) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement ps = conn.connect().prepareStatement(
				      "delete from Patient where patient_id = ?");

				    int patient_id = pat.getPatient_id();
					// set the preparedstatement parameters
				    ps.setInt(1,patient_id);
				    
				    // call executeUpdate to execute our sql update statement
				    ps.executeUpdate();
				    ps.close();
				    conn.connect().close();
			} catch (SQLException se) {// Handle errors for JDBC
				se.printStackTrace();
			}
		
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

	public Patient find(Class<Patient> class1, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll(Class clazz) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement statement = conn.connect().prepareStatement("select * from Patient");    
			   
			 rs = statement.executeQuery();
			 ArrayList<Patient> list = new ArrayList<Patient>( );
			 
			
			 while(rs.next())
	            {
	                Patient user = new Patient();
	                user.setPatient_id( rs.getInt("patient_id") );
	                user.setPatient_name( rs.getString("patient_name") );
	                user.setPatient_add( rs.getString("patient_add") );
	                user.setPatient_phone( rs.getString("patient_phone") );
	                user.setPatient_email( rs.getString("patient_email") );
	                user.setUid( rs.getInt("uid") );
	                list.add(user);
	                
	            }
			 conn.connect().close();
			return list;
			// close db connection
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}

	public List<Patient> userfindAll(Person user) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement statement = conn.connect().prepareStatement("select * from Patient where uid = ?");    
			statement.setInt(1, user.getUid());   
			 rs = statement.executeQuery();
			 ArrayList<Patient> list = new ArrayList<Patient>( );
			 
			
			 while(rs.next())
	            {
	                Patient pat = new Patient();
	                pat.setPatient_id( rs.getInt("patient_id") );
	                pat.setPatient_name( rs.getString("patient_name") );
	                pat.setPatient_add( rs.getString("patient_add") );
	                pat.setPatient_phone( rs.getString("patient_phone") );
	                pat.setPatient_email( rs.getString("patient_email") );
	                pat.setUid( rs.getInt("uid") );
	                list.add(pat);
	                
	            }
			 conn.connect().close();
			return list;
			// close db connection
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}

	public void userupdate(Patient item) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement ps = conn.connect().prepareStatement(
				      "UPDATE Patient SET patient_id = ?, patient_name = ?, patient_add = ?, patient_phone = ?,patient_email= ? WHERE patient_id = ?");

				    int patient_id = item.getPatient_id();
					// set the preparedstatement parameters
				    ps.setInt(1,patient_id);
				    String patient_name = item.getPatient_name();
					ps.setString(2,patient_name);
				    String patient_add = item.getPatient_add();
					ps.setString(3,patient_add);
				    String patient_phone = item.getPatient_phone();
					ps.setString(4,patient_phone);
				    String patient_email = item.getPatient_email();
					ps.setString(5,patient_email);
				   
				    int patient_ido = item.getPatient_id();
					ps.setInt(6,patient_ido);
				    
				    // call executeUpdate to execute our sql update statement
				    ps.executeUpdate();
				    ps.close();
				    conn.connect().close();
			} catch (SQLException se) {// Handle errors for JDBC
				se.printStackTrace();
			}
		
	}

	

}
