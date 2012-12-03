package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Accueil extends Activity implements OnClickListener{
	Button btnAjout = null;
	Button btnStatistiques = null;
	Button btnProfil = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		btnAjout = (Button) findViewById(R.id.btnAjout);
		btnAjout.setOnClickListener(this);
		btnStatistiques = (Button) findViewById(R.id.btnStatistiques);
		btnStatistiques.setOnClickListener(this);
		btnProfil = (Button) findViewById(R.id.btnProfil);
		btnProfil.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_accueil, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnStatistiques) {
			Intent monIntent = new Intent(this,Statistiques.class);
			startActivity(monIntent);
		}
		if (v == btnAjout) {
			Intent monIntent = new Intent(this,AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == btnProfil) {
			Intent monIntent = new Intent(this,Profil.class);
			startActivity(monIntent);
		}
	}
}
