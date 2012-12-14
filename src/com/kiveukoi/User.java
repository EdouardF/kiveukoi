package com.kiveukoi;
 
/**
 * Création d'un livre tout simple pour un exemple d'utilisation de SQLite sous Android
 * @author Axon
 * http://www.tutomobile.fr
 */
public class User {
	
	private String login;
	private String password;
	private String pin;
 
	public User(){}
 
	public User(String login, String password, String pin){
		this.login = login;
		this.password = password;
		this.pin = pin;
	}
 
	public String getLogin() {
		return login;
	}
 
	public void setLogin(String login) {
		this.login = login;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getPIN() {
		return pin;
	}
 
	public void setPIN(String pin) {
		this.pin = pin;
	}
 
	public String toString(){
		return "ID : "+login+"\nISBN : "+password+"\nTitre : "+pin;
	}
}