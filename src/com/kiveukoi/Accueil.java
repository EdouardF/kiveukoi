package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Accueil extends Activity implements OnClickListener {
	Button btnAjout = null;
	Button btnStatistiques = null;
	Button btnProfil = null;
	ProgressBar progressBar1 = null;
	ProgressBar progressBar2 = null;
	ProgressBar progressBar3 = null;
	ProgressBar progressBar4 = null;
	ProgressBar progressBar5 = null;
	ProgressBar progressBar6 = null;
	ProgressBar progressBar7 = null;
	ProgressBar progressBar8 = null;
	ProgressBar progressBar9 = null;
	ProgressBar progressBar10 = null;
	ProgressBar progressBar11 = null;
	TextView neufh = null;

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
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar1.setOnClickListener(this);
		progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
		progressBar2.setOnClickListener(this);
		progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
		progressBar3.setOnClickListener(this);
		progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
		progressBar4.setOnClickListener(this);
		progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);
		progressBar5.setOnClickListener(this);
		progressBar6 = (ProgressBar) findViewById(R.id.progressBar6);
		progressBar6.setOnClickListener(this);
		progressBar7 = (ProgressBar) findViewById(R.id.progressBar7);
		progressBar7.setOnClickListener(this);
		progressBar8 = (ProgressBar) findViewById(R.id.progressBar8);
		progressBar8.setOnClickListener(this);
		progressBar9 = (ProgressBar) findViewById(R.id.progressBar9);
		progressBar9.setOnClickListener(this);
		progressBar10 = (ProgressBar) findViewById(R.id.progressBar10);
		progressBar10.setOnClickListener(this);
		progressBar11 = (ProgressBar) findViewById(R.id.progressBar11);
		progressBar11.setOnClickListener(this);
		neufh = (TextView) findViewById(R.id.neufh);
		neufh.setOnClickListener(this);

		/* Alignement des boutons */
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		btnAjout.setWidth(metrics.widthPixels / 3);
		btnStatistiques.setWidth(metrics.widthPixels / 3);
		btnProfil.setWidth(metrics.widthPixels / 3);

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
			Intent monIntent = new Intent(this, Statistiques.class);
			startActivity(monIntent);
		}
		if (v == btnAjout) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == btnProfil) {
			Intent monIntent = new Intent(this, Profil.class);
			startActivity(monIntent);
		}
		if (v == progressBar1) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar2) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar3) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar4) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar5) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar6) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar7) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar8) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar9) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar10) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
		if (v == progressBar11) {
			Intent monIntent = new Intent(this, AjoutModif.class);
			startActivity(monIntent);
		}
	}
}