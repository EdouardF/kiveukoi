package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Accueil extends Activity implements OnClickListener{
	Button btnAjout = null;
	Button btnStatistiques = null;
	Button btnProfil = null;
	Button btnDeco = null;
	
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
		
		/**
		 * Alignement des boutons
		 */
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		btnStatistiques.setWidth(metrics.widthPixels/3);
		btnAjout.setWidth(metrics.widthPixels/3);
		btnProfil.setWidth(metrics.widthPixels/3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_accueil, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.deconnexion:
				try {
					UserDataBase l_dataBase = new UserDataBase(this);
					l_dataBase.open();
					User l_user = l_dataBase.getUser();
					if (l_user != null) {
						// purger la base
						l_dataBase.removeUser();
						finish();
					}
					l_dataBase.close();
				} catch (SQLException sqle) {
					Toast.makeText(this, "Déconnexion impossible", Toast.LENGTH_SHORT).show();
				}
				// déconnexion
				return true;
		}
		return super.onMenuItemSelected(featureId, item);
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
