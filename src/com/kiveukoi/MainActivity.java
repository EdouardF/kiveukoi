package com.kiveukoi;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnConnexion = null;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		btnConnexion = (Button) findViewById(R.id.btnConnexion);

		btnConnexion.setOnClickListener(this);
		
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		EditText editText2 = (EditText)findViewById(R.id.editText2);
		EditText editText3 = (EditText)findViewById(R.id.editText3);
		
		/**
		 * Passer d'une case à la suivante
		 * Quand le premier est rempli, on passe au second
		 */
		editText1.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void afterTextChanged(Editable s) {
		    	EditText editText2 = (EditText)findViewById(R.id.editText2);
		    	editText2.requestFocus();
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				EditText editText2 = (EditText)findViewById(R.id.editText2);
		    	editText2.requestFocus();
			}
		});
		/**
		 * Quand le deuxième est rempli, on passe au troisième
		 */
		editText2.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void afterTextChanged(Editable s) {
		    	EditText editText3 = (EditText)findViewById(R.id.editText3);
		    	editText3.requestFocus();
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				EditText editText3 = (EditText)findViewById(R.id.editText3);
		    	editText3.requestFocus();
			}
		});
		/**
		 * Quand la troisième est rempli, on passe au dernier
		 */
		editText3.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void afterTextChanged(Editable s) {
		    	EditText editText4 = (EditText)findViewById(R.id.editText4);
		    	editText4.requestFocus();
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				EditText editText4 = (EditText)findViewById(R.id.editText4);
		    	editText4.requestFocus();
			}
		});
	}
	
	/**
	 * Vérifie que le code PIN est renseigné pour accéder à l'accueil
	 */
	@Override
	public void onClick(View v) {
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		String content1 = editText1.getText().toString();

		EditText editText2 = (EditText)findViewById(R.id.editText2);
		String content2 = editText2.getText().toString();
		
		EditText editText3 = (EditText)findViewById(R.id.editText3);
		String content3 = editText3.getText().toString();
		
		EditText editText4 = (EditText)findViewById(R.id.editText4);
		String content4 = editText4.getText().toString();
		
		/**
		 * On crée le code PIN à partir des 4 chiffres saisis
		 */
		// String pin = content1+content2+content3+content4;
		
		/**
		 * Si tous les champs ne sont pas remplis
		 * @TODO si tous les champs sont remplis, on compare le code donné avec celui dans la base et on renvoie true => on va à l'accueil
		 */
		if (!content1.matches("") && !content2.matches("") && !content3.matches("") && !content4.matches("")) {
			if (v == btnConnexion) {
				Intent monIntent = new Intent(this,Accueil.class);
				startActivity(monIntent);
			}
		} else {
			Toast.makeText(this, "Code incorrect", Toast.LENGTH_SHORT).show();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
