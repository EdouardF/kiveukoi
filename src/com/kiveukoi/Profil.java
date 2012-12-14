package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profil extends Activity implements OnClickListener {
	Button btnModifPIN = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		
		btnModifPIN = (Button)findViewById(R.id.btnModifPIN);
		btnModifPIN.setOnClickListener(this);
		
		/**
		 * passer d'une case à l'autre pour la saisie du code PIN
		 */
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		EditText editText2 = (EditText)findViewById(R.id.editText2);
		
		/**
		 * Passer d'une case à la suivante
		 * Quand le premier est rempli, on passe au second
		 */
		editText1.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void afterTextChanged(Editable s) {
		    	String code = s.toString();
		    	if (!code.matches("") && (code.length() == 4)) {
		    		EditText editText2 = (EditText)findViewById(R.id.editText2);
		    		editText2.requestFocus();
		    	}
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
		});
		/**
		 * Quand le deuxième est rempli, on passe au troisième
		 */
		editText2.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void afterTextChanged(Editable s) {
		    	String code = s.toString();
		    	if (!code.matches("") && (code.length() == 4)) {
		    		EditText editText3 = (EditText)findViewById(R.id.editText3);
		    		editText3.requestFocus();
		    	}
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void onClick(View v) {
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		String actuel = editText1.getText().toString();

		EditText editText2 = (EditText)findViewById(R.id.editText2);
		String new1 = editText2.getText().toString();
		
		EditText editText3 = (EditText)findViewById(R.id.editText3);
		String new2 = editText3.getText().toString();
		
		boolean modifok = checkPIN(actuel, new1, new2);
		
		/**
		 * Si tous les champs ne sont pas remplis
		 * @TODO si tous les champs sont remplis, on compare le code donné avec celui dans la base et on renvoie true => on va à l'accueil
		 */
		if (!actuel.matches("") && !new1.matches("") && !new2.matches("")) {
			if (modifok) {
				if (v == btnModifPIN) {
					Intent monIntent = new Intent(this,Accueil.class);
					startActivity(monIntent);
					Toast.makeText(this, "Code secret mis à jour", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, "Les 2 codes entrés ne correspondent pas", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * On vérifie les codes PIN
	 * @param actuel Code PIN actuel
	 * @param new1 Code PIN nouveau 1
	 * @param new2 Code PIN nouveau 2
	 * @return vrai si le code a été changé, faux sinon
	 */
	public boolean checkPIN(String actuel, String new1, String new2) {
		UserDataBase l_dataBase = new UserDataBase(this);
		l_dataBase.open();
		User l_user = l_dataBase.getUser();
		
		// on vérifie dans la base que actuel est ok
		// si il est ok
		if (actuel.matches(l_user.getPIN())) {
			if (new1.matches(new2)) {
				l_user.setPIN(new1);
				l_dataBase.updateUser(l_user);
			}
			return new1.matches(new2);
		} else if (!actuel.matches("")){
			Toast.makeText(this, "Le code actuel est incorrect", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profil, menu);
		return true;
	}
}
