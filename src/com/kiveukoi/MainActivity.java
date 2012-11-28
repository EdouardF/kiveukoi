package com.kiveukoi;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

	@Override
	public void onClick(View v) {
		if (v == btnConnexion) {
			Intent monIntent = new Intent(this,Accueil.class);
			startActivity(monIntent);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
