package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AjoutModif extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_modif);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ajout_modif, menu);
		return true;
	}

}
