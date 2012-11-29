package com.kiveukoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

	}

	/**
	 * Vérifie que le code PIN est renseigné pour accéder à l'accueil
	 */
	@Override
	public void onClick(View v) {
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		String content1 = editText1.getText().toString();

		EditText editText2 = (EditText) findViewById(R.id.editText2);
		String content2 = editText2.getText().toString();

		EditText editText3 = (EditText) findViewById(R.id.editText3);
		String content3 = editText3.getText().toString();

		EditText editText4 = (EditText) findViewById(R.id.editText4);
		String content4 = editText4.getText().toString();

		if (!content1.matches("") && !content2.matches("")
				&& !content3.matches("") && !content4.matches("")) {
			if (v == btnConnexion) {
				Intent monIntent1 = new Intent(this, Accueil.class);
				startActivity(monIntent1);
			}
		} else {
			Toast.makeText(this, "Code incorrect", Toast.LENGTH_LONG).show();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
