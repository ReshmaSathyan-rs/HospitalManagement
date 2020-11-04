package models;


/**
 * 
 * Class describing database entity User
 *
 */


public class Person {
	
	int uid;
	String uname;
	String upassword;
	Boolean isadmin;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public Boolean getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}
	
}