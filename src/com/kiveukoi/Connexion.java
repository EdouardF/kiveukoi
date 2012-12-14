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
	
			this.m_code1 = (EditText) findViewById(R.id.txtSecret1);
			this.m_code2 = (EditText) findViewById(R.id.txtSecret2);
			this.m_login = (EditText) findViewById(R.id.txtLogin);
			this.m_password = (EditText) findViewById(R.id.txtPassword);
			this.m_btnConnexion = (Button) findViewById(R.id.btnConnexion);

			// Si le texte change
			this.m_code1.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
					m_code2.requestFocus();
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
		try{
			if(arg0 == this.m_btnConnexion){
				//Recupere le login et le mot de passe
				String l_login=this.m_login.toString();
				String l_password=this.m_password.toString();
				if(this.verifieLogin()){
		
					//Ajoute les donnees dans la base sqlite
					this.addDataSql(l_login,l_password,this.m_code1.toString());
		
					//Termine l'activity
					finish();
				}
			}
		}catch(Exception ex){
			Toast.makeText(this, "Erreur : "+ex.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Verifie que le login et le mot de passe sont correct
	 * 
	 * @return Vrai si le couple est correct
	 */
	private boolean verifieLogin() {
		return this.m_code1.toString() == this.m_code2.toString();
	}

	/**
	 * Ajoute les donnees dans la base SQLite
	 * @param _login Login de l'utilisateur
	 * @param _password Password de l'utilisateur
	 * @param _code Code PIN
	 * @throws Exception 
	 */
	private void addDataSql(String _login,String _password,String _code) throws Exception{
		try{
			UserDataBase l_dataBase=new  UserDataBase(this);
			User l_user=new User(_login,_password,_code);
			l_dataBase.open();
			l_dataBase.insertUser(l_user);
			l_dataBase.close();
		}catch(Exception ex){
			Log.e("Erreur", ex.toString());
		}
	}
}
