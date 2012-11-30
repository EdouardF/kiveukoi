package com.kiveukoi;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
public class AjoutModif extends Activity {
    /** Private members of the class */
    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	static final int DATE_DIALOG_ID = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_modif);
 
        /** Capture our View elements */
        pDisplayDate = (TextView) findViewById(R.id.displayDate);
        pPickDate = (Button) findViewById(R.id.pickDate);
 
        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
 
        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
 
        /** Display the current date in the TextView */
        updateDisplay();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ajout_modif, menu);
		return true;
	}



	    /** Callback received when the user "picks" a date in the dialog */
	    private DatePickerDialog.OnDateSetListener pDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {
	 
	                public void onDateSet(DatePicker view, int year, 
	                                      int monthOfYear, int dayOfMonth) {
	                    pYear = year;
	                    pMonth = monthOfYear;
	                    pDay = dayOfMonth;
	                    updateDisplay();
	                    displayToast();
	                }
	            };
	     
	    /** Updates the date in the TextView */
	    private void updateDisplay() {
	        pDisplayDate.setText(
	            new StringBuilder()
	                    // Month is 0 based so add 1
	                    .append(pMonth + 1).append("/")
	                    .append(pDay).append("/")
	                    .append(pYear).append(" "));
	    }
	     
	    /** Displays a notification when the date is updated */
	    private void displayToast() {
	        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate.getText()),  Toast.LENGTH_SHORT).show();
	             
	    }        
	     
	    /** Create a new dialog for date picker */
	    @Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        pYear, pMonth, pDay);
	        }
	        return null;
	    }
	}


