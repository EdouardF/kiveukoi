package com.kiveukoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDataBase {
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "kiveukoi.db";
 
	private static final String TABLE_USER = "table_user";
	private static final String COL_ID = "ID";
	private static final String COL_LOGIN = "LOGIN";
	private static final int NUM_COL_LOGIN = 1;
	private static final String COL_PASSWORD = "PASSWORD";
	private static final int NUM_COL_PASSWORD = 2;
	private static final String COL_SECRET = "SECRET";
	private static final int NUM_COL_SECRET = 3;
 
	private SQLiteDatabase bdd;
 
	private DataBase baseSQLite;
 
	public UserDataBase(Context context){
		//On créer la BDD et sa table
		baseSQLite = new DataBase(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en écriture
		bdd = baseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertUser(User user){
		long l_return;
		if(this.getUser()!=null){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			values.put(COL_LOGIN, user.getLogin());
			values.put(COL_PASSWORD, user.getPassword());
			values.put(COL_SECRET, user.getSecret());
			//on insère l'objet dans la BDD via le ContentValues
			l_return = bdd.insert(TABLE_USER, null, values);
		}else{
			l_return = this.updateUser(user);
		}
		return l_return;
		
	}
 
	public int updateUser(User user){
		//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, user.getLogin());
		values.put(COL_PASSWORD, user.getPassword());
		values.put(COL_SECRET, user.getSecret());
		return bdd.update(TABLE_USER, values, COL_ID + " = 0", null);
	}
 
	public int removeUser(){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(TABLE_USER, COL_ID + " = 0", null);
	}
 
	public User getUser(){
		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
		Cursor c = bdd.query(TABLE_USER, new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_SECRET}, COL_ID + " = 0 ", null, null, null, null);
		return cursorToUser(c);
	}
	
	private User cursorToUser(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		User user = new User();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		user.setLogin(c.getString(NUM_COL_LOGIN));
		user.setPassword(c.getString(NUM_COL_PASSWORD));
		user.setSecret(c.getString(NUM_COL_SECRET));
		//On ferme le cursor
		c.close();

		return user;
	}

}
