/*Author: Reshma Sathyanarayanan
 * Date : 04/13/2020
 * File Name:DBConnect.java
 * Lab number: Object Oriented Application Development ITMD 510- Lab 4
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//To Create DBConnect Class
public class DBConnect {

	// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "fp510", PASS = "510";
	
	//Connecting to database
	public Connection connect() throws SQLException {

		return DriverManager.getConnection(DB_URL, USER, PASS);

	}

}
