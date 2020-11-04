package models;

import java.util.List;
import models.Person;

/**
 * 
 * Class defining database methods for the User class
 *
 */
public class Persondao extends PersonDBOperations {
	

	public void updateData(Person user) {
		super.update(user);
	}

	public void deleteData(Person user) {
		super.delete(user);
	}

//	public User getById(int Id) {
//		int id = Id;
//		return (User) super.find(User.class, id);
//	}

	public Person getByName(String name) {

		return (Person) super.findByName( name);
	}

//	public List getRecords(User user) {
//		return super.findAll(User.class);
//	}
	@Override
	public void insert(Patient obj) {
		// TODO Auto-generated method stub
		
	}

@Override
public void insert(Person obj) {
	// TODO Auto-generated method stub
	
}



	

	

	

	
	

}
