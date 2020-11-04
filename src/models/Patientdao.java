package models;

import java.sql.SQLException;
import java.util.List;

import models.Patient;



/**
 * 
 * Class defining database methods for the Medicine class
 *
 */
public  class Patientdao extends PatientDBOperations
{   
    public void insertData(Patient pat) throws SQLException 
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
    
    public List getRecords(Patient pat) 
    {
       return  super.findAll(Patient.class);
    }

	public List<Patient> usergetRecords(Person user) {
		// TODO Auto-generated method stub
		return  super.userfindAll(user);
	}

	public void userupdate(Patient item) {
		// TODO Auto-generated method stub
		super.userupdate(item); 
	}

   

}
	