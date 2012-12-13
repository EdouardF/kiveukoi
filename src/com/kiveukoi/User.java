package com.kiveukoi;

public class User {
    
	private String login;
	private String password;
	private String secret;
	
	public User(){}
	/**
	 * Constructeur de la la classe user
	 * @param login Login
	 * @param password Mot de passe
	 * @param secret Code secret
	 */
	public User(String login, String password, String secret) {
		super();
		this.login = login;
		this.password = password;
		this.secret = secret;
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
		return secret;
	}
	public void setPIN(String secret) {
		this.secret = secret;
	}

}
