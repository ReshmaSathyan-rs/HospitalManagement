package models;

import java.sql.SQLException;
import java.util.List;

public class Appointmentdao extends AppointmentDBOperations {
	 public void insertData(Appointment pat) throws SQLException 
	    {
	        super.insert(pat);       
	    }
	 
	    public void updateData(Patient pat) 
	    {
	        super.update(pat);       
	    }
	    
	    public void deleteData(Patient pat) 
	    {
	        super.delete(pat);
	    }
	    
	    public Patient getById(int Id) 
	    {  
	    	int id =Id;
	       return (Patient) super.find(Patient.class,id);
	    }
	    
	    public List getRecords() throws SQLException 
	    {
	       return  super.findAll(AppointmentWithDoctorAndPatient.class);
	    }

		public List<AppointmentWithDoctorAndPatient> usergetRecords(Person user) throws SQLException {
			// TODO Auto-generated method stub
			return  super.userfindAll(user);
		}

		public void userupdate(Patient item) {
			// TODO Auto-generated method stub
			super.userupdate(item); 
		}

		public void update(AppointmentWithDoctorAndPatient item) {
			// TODO Auto-generated method stub
			super.update(item);
		}

		public void delete(AppointmentWithDoctorAndPatient pat) {
			// TODO Auto-generated method stub
			super.delete(pat);
			
		}

}
