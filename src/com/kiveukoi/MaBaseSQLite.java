package com.kiveukoi;

	import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 
public class MaBaseSQLite extends SQLiteOpenHelper {
 
	private static final String TABLE_USER = "table_User";
	private static final String COL_ID = "ID";
	private static final String COL_LOGIN = "login";//idsn
	private static final String COL_PASSWORD = "password";//titre
	private static final String COL_CODEPIN = "codePin";
 
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_USER + " ("
	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ COL_LOGIN + " TEXT NOT NULL, "
	+ COL_PASSWORD + " TEXT NOT NULL, "
	+ COL_CODEPIN + " TEXT NOT NULL, ";
 
	public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on crée la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_USER + ";");
		onCreate(db);
	}
 
}
