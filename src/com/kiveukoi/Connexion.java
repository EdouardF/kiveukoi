package com.kiveukoi;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends Activity implements OnClickListener {

	private EditText m_code1;
	private EditText m_code2;
	private Button m_btnConnexion;
	private EditText m_login;
	private EditText m_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);

		try {
			// Assignation
			this.m_code1=(EditText)findViewById(R.id.txtSecret1);
			this.m_code2=(EditText)findViewById(R.id.txtSecret2);
			this.m_login=(EditText)findViewById(R.id.txtLogin);
			this.m_password=(EditText)findViewById(R.id.txtPassword);
			this.m_btnConnexion=(Button)findViewById(R.id.btnLogin);
			this.m_btnConnexion.setOnClickListener(this);
			// Si le texte change
			this.m_code1.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
					if (s.length() == 4) {
						m_code2.requestFocus();
					}
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub

				}

			});
			this.m_code2.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {

				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub

				}

			});
		} catch (Exception ex) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_connexion, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		this.m_code1=(EditText)findViewById(R.id.txtSecret1);
		this.m_code2=(EditText)findViewById(R.id.txtSecret2);
		this.m_login=(EditText)findViewById(R.id.txtLogin);
		this.m_password=(EditText)findViewById(R.id.txtPassword);
		this.m_btnConnexion=(Button)findViewById(R.id.btnLogin);
		try{
			if(arg0 == this.m_btnConnexion){
				//Recupere le login et le mot de passe
				String l_login=this.m_login.getText().toString();
				String l_password=this.m_password.getText().toString();
				String l_code1=this.m_code1.getText().toString();
				String l_code2=this.m_code2.getText().toString();
				
				boolean loginok = verifiePIN(l_code1, l_code2);
				if(loginok && !l_login.matches("") && !l_password.matches("") 
						&& !l_code1.matches("") && !l_code2.matches("")){
					//Ajoute les donnees dans la base sqlite
					if (this.addDataSql(l_login ,l_password, l_code1)) {
						//Termine l'activity
						finish();
						Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(this, "Echec de l'authentification", Toast.LENGTH_SHORT).show();
				}
			}
		}catch(Exception ex){
			Toast.makeText(this, "Erreur : "+ex.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Verifie que les codes PIN sont identiques
	 * @return Vrai si le couple est correct
	 */
	private boolean verifiePIN(String code1, String code2) {
		return code1.matches(code2);
	}

	/**
	 * Ajoute les donnees dans la base SQLite
	 * @param _login Login de l'utilisateur
	 * @param _password Password de l'utilisateur
	 * @param _code Code PIN
	 * @throws Exception 
	 */
	private boolean addDataSql(String _login,String _password,String _code) throws Exception{
		try{
			UserDataBase l_dataBase=new  UserDataBase(this);
			User l_user=new User(_login,_password,_code);
			l_dataBase.open();
			l_dataBase.insertUser(l_user);
			l_dataBase.close();
		}catch(Exception ex){
			Log.e("Erreur", ex.toString());
		}
		return true;
	}
}
