package com.kiveukoi;

/**
 * Création d'un Utilisateur Pour application Kiveukoi
 * @author Matthieu
 */

public class User {
	
	//Initialisation des attribut de la classe User
	private int id;
	private String login;
	private String password;
	private String codePin;
	// Premier Constructeur
	public User(){}
	// Second Constructeur contenant des attribut
	public User(String login, String password, String codePin){
		this.login = login;
		this.password = password;
	}
 // récupère l'ID
	public int getId() {
		return id;
	}
 // Envoi l'ID
	public void setId(int id) {
		this.id = id;
	}
 // Récupère le Login
	public String getLogin() {
		return login;
	}
	// Envoi le Login
	public void setLogin(String login) {
		this.login = login;
	}
	// Récupère le Password
	public String getPassword() {
		return password;
	}
	// Envoi le Password
	public void setPassword(String password) {
		this.password = password;
	}
	// Récupère le codePin
	public void setCodePin(String codePin) {
		this.codePin = codePin;
	}
	// Envoi le codePin
	public String getCodePin() {
		return codePin;
	}
 
 // Renvoi la totalité des informations
	public String toString(){
		return "ID : "+id+"" +
				"\nLOGIN : "+login+"" +
						"\nPASSWORD: "+password+"" +
								"\nCODEPIN : "+codePin+"";
	}
}
