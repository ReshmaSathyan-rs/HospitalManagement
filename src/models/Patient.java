package models;


/**
 * 
 * Class describing database entity Medicine
 *
 */

public class Patient
{
	
	int patient_id;
	String patient_name;
	String patient_add;
	String patient_phone;
	String patient_email;
	int uid;
	
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPatient_add() {
		return patient_add;
	}
	public void setPatient_add(String patient_add) {
		this.patient_add = patient_add;
	}
	public String getPatient_phone() {
		return patient_phone;
	}
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	public String getPatient_email() {
		return patient_email;
	}
	public void setPatient_email(String patient_email) {
		this.patient_email = patient_email;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String toString() {
		return this.patient_name;
	}
	

}
