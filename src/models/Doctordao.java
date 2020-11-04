package models;

import java.sql.SQLException;
import java.util.List;

import models.Patient;



/**
 * 
 * Class defining database methods for the Medicine class
 *
 */
public  class Doctordao extends DoctorDBOperations
{   
    public void insertData(Doctor doc) 
    {
        super.insert(doc);       
    }
 
    public void updateData(Doctor doc) 
    {
        super.update(doc);       
    }
    
    public void deleteData(Doctor doc) 
    {
        super.delete(doc);
    }
    
    public Doctor getById(int Id) 
    {  
    	int id =Id;
       return (Doctor) super.find(Doctor.class,id);
    }
    
    public List getRecords(Doctor doc) throws SQLException 
    {
       return  super.findAll(Doctor.class);
    }

	public List<Doctor> usergetRecords(Doctor doc) {
		// TODO Auto-generated method stub
		return  super.userfindAll(doc);
	}

	public void userupdate(Doctor item) {
		// TODO Auto-generated method stub
		super.userupdate(item); 
	}

	
	
    
    

}
	