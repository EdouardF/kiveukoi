package com.kiveukoi;
 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class UserDataBase {
     
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "kiveukoi.db";
 
	private static final String TABLE_USERS = "table_user";
	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LOGIN = "login";
	private static final int NUM_COL_LOGIN = 1;
	private static final String COL_PASSWORD = "password";
	private static final int NUM_COL_PASSWORD = 2;
	private static final String COL_PIN = "pin";
	private static final int NUM_COL_PIN = 3;
 
	private SQLiteDatabase bdd;
 
	private DataBase BaseSQLite;
 
	public UserDataBase(Context context){
		//On crÃ©e la BDD et sa table
		BaseSQLite = new DataBase(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en Ã©criture
		bdd = BaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accÃ¨s Ã  la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertUser(User user){
		if(!this.possedeData()){
			//CrÃ©ation d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associÃ©e Ã  une clÃ© (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			values.put(COL_LOGIN, user.getLogin());
			values.put(COL_PASSWORD, user.getPassword());
			values.put(COL_PIN, user.getPIN());
			//on insÃ¨re l'objet dans la BDD via le ContentValues
			return bdd.insert(TABLE_USERS, null, values);
		}else{
			return this.updateUser(user);
		}
	}
 
	public int updateUser(User user){
		//La mise Ã  jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement prÃ©ciser quel livre on doit mettre Ã  jour grÃ¢ce Ã  l'ID
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, user.getLogin());
		values.put(COL_PASSWORD, user.getPassword());
		values.put(COL_PIN, user.getPIN());
		return bdd.update(TABLE_USERS, values, COL_ID + " = " + this.getId(), null);
	}
 
	public int removeUser(){
		//Suppression d'un user de la BDD grÃ¢ce Ã  l'ID
		return bdd.delete(TABLE_USERS, COL_ID + " = " + this.getId(), null);
	}
 
	public User getUser(){
		//RÃ©cupÃ¨re dans un Cursor les valeurs correspondant à  un user contenu dans la BDD
		Cursor c = bdd.query(TABLE_USERS, new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_PIN}, null, null, null, null, null);
		return cursorToUser(c);
	}
	
	private int getId(){
		int l_return;
		Cursor c = bdd.query(TABLE_USERS, new String[] {COL_ID}, null, null, null, null, null);
		if(c.getCount() == 0){
			l_return=1;
		}else{
			c.moveToFirst();
			l_return = c.getInt(NUM_COL_ID);
		}
		return l_return;
	}
	
	public boolean possedeData(){
		Cursor c = bdd.query(TABLE_USERS, new String[] {COL_ID}, null, null, null, null, null, "1");
		return c.getCount() != 0;
	}
	
	//Cette méthode permet de convertir un cursor en un user
	private User cursorToUser(Cursor c){
		//si aucun élèment n'a été retourné dans la requète, on renvoie null
		if (c.getCount() == 0){
			return null;
		}else{
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un livre
			User user = new User();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			user.setLogin(c.getString(NUM_COL_LOGIN));
			user.setPassword(c.getString(NUM_COL_PASSWORD));
			user.setPIN(c.getString(NUM_COL_PIN));
			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return user;
		}
	}
}