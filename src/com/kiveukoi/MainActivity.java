package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Creation d'une instance de ma classe UserBDD
        UserBDD userBdd = new UserBDD(this);
 
        //Creation d'un user
        User user = new User("matthieu", "testPassword", "123456789");
 
        //On ouvre la base de donnees pour ecrire dedans
        userBdd.open();
        
        //On insere le livre que l'on vient de creer
        userBdd.insertUser(user);
 
        //Pour verifier que l'on a bien cree notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a cree precedemment
        User userFromBdd = userBdd.getUserWithLogin(user.getLogin());
        // User userFromBdd = userBdd.getUserWithId(user.getId()); ligne initiale
        
        //Si un livre est retourne (donc si le livre à bien ete ajoute à la BDD)
        if(userFromBdd != null){
        	//On affiche les infos du livre dans un Toast
        	Toast.makeText(this, userFromBdd.toString(), Toast.LENGTH_LONG).show();
        	
        	/*//On modifie le titre du livre
        	userFromBdd.setLogin("J'ai modifie le login du user");
        	//Puis on met à jour la BDD
            userBdd.updateUser(userFromBdd.getId(), userFromBdd);*/
        } 
        	userBdd.close();
   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}

