package controllers;


import models.Person;

public final class UserSession {

		    private static UserSession instance;

		    private Person user; 
		    public Person getUser() {
				return user;
			}

			

		    private UserSession(Person user) {
		        this.user = user;
		       
		    }

		    public static UserSession getInstance(Person user) {
		        if(instance == null) {
		            instance = new UserSession(user);
		        }
		        return instance;
		    }

		    public static UserSession getInstance(){
				return instance;
		    	
		    }



			public static boolean isAdmin() {
				// TODO Auto-generated method stub
				UserSession  usr = UserSession.getInstance();
				return usr.getUser().getIsadmin();
			}

		    

		   
		}

