package com.kiveukoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserBDD {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "UserKiveukoi";
 
	
	private static final String TABLE_USER = "table_User";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LOGIN = "login";//idsn
	//private static final int NUM_COL_LOGIN = 1;
	private static final String COL_PASSWORD = "password";//titre
	//private static final int NUM_COL_PASSWORD = 2;
	private static final String COL_CODEPIN = "codePin";
	//private static final int NUM_COL_CODEPIN = 3;
 
	private SQLiteDatabase bdd;
 
	private MaBaseSQLite maBaseSQLite;
 
	public UserBDD(Context context){
		//On crée la BDD et sa table
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertUser(User user){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_LOGIN, user.getLogin());
		values.put(COL_PASSWORD, user.getPassword());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_USER, null, values);
	}
 
	public int updateUser(int id, User user){
		//La mise à jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement préciser quel user on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, user.getLogin());
		values.put(COL_PASSWORD, user.getPassword());
		return bdd.update(TABLE_USER, values, COL_ID + " = " +id, null);
	}
 
	public int removeUserWithID(int id){
		//Suppression d'un user de la BDD grâce à l'ID
		return bdd.delete(TABLE_USER, COL_ID + " = " +id, null);
	}
 
	/*public User getUserWithId(String login){
		//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son id)
		Cursor c = bdd.query(TABLE_USER, new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_PASSWORD + " LIKE \"" + login +"\"", null, null, nullL);
		//Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
		//return cursorToLivre(c);
		return cursorToUser(c);
	}*/
	
	//METHODE INITIALE
	 public User getUserWithLogin(String login){
		//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son titre)
		Cursor c = bdd.query(TABLE_USER, new String[] {COL_ID, COL_LOGIN, COL_PASSWORD, COL_CODEPIN}, COL_LOGIN + " LIKE \"" + login +"\"", null, null, null, null);
		return cursorToUser(c);
	 }
 
	//Cette méthode permet de convertir un cursor en un user
	private User cursorToUser(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un user
		User user = new User();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		user.setId(c.getInt(NUM_COL_ID));
		/*User.setLogin(c.getString(NOM_COL_LOGIN)); Commentaire cause -> erreur
		User.setPassword(c.getString(NOM_COL_PASSWORD));
		User.setCodePin(c.getString(NOM_COL_CODEPIN));//On ferme le cursor*/
		c.close();
 
		//On retourne le user
		return user;
	}
}