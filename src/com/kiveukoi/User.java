package com.kiveukoi;
 
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  
  
public class User extends SQLiteOpenHelper {  
  
    // Définition de la version de la base ainsi que de son nom de fichier
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;  
          
    // Utilisation d'une classe statique pour definir la structure de la table
        private static class UserTable {  
        private static final String NAME = "user";  
        private static final String COL_ID = "id";  
        private static final String COL_LOGIN = "login";  
        private static final String COL_PASSWORD = "password";  
        private static final String COL_CODEPIN = "codePin"; 
        }  
  
    private SQLiteDatabase db;  
   
    //Constructeur de la classe 
    public User(Context context) {  
  
        super(context, DB_NAME, null, DB_VERSION);  
        // Android verifie si la base de donnees est defini via le nom de cette base
        // Si il ne la trouve pas il appel la methode onCreate  
        this.db = this.getWritableDatabase();  
  
    }  
  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        // Android creer la base de donnees qui est identifie par la variable DB_NAME  
        // Ensuite creation du schema de cette table
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT,%s TEXT)",  
                        UserTable.NAME, 
                        UserTable.COL_ID,  
                        UserTable.COL_LOGIN,
                        UserTable.COL_PASSWORD,
                        UserTable.COL_CODEPIN));  
  
    }  
    

      // creation des cursors
    public String[] getUserCredentials() throws Exception {  
  
        String[] creds;  
        Cursor cursor;  
  
        creds = new String[4];  
  
        cursor = this.db.query(UserTable.NAME, new String[] {  
                        UserTable.COL_ID,
                        UserTable.COL_LOGIN, 
                UserTable.COL_PASSWORD,
                UserTable.COL_CODEPIN},   
                null, null, null, null, null);  
  
        if (cursor.moveToFirst()) {  
  
            creds[0] = cursor.getString(0);  
            creds[1] = cursor.getString(1);  
            creds[2] = cursor.getString(2);  
            creds[3] = cursor.getString(3);  
            cursor.close();  
              
        } else {  
              
            throw new Exception("Aucune données");  
              
        }  
  
        return creds;  
  
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {  
        // Utile si vous voulez changer de version
        // Cette methode sera appeler pour importer votre base de donnees
    }        
}  
