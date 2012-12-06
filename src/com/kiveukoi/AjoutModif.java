package com.kiveukoi;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


public class AjoutModif extends Activity {
	private TextView pDisplayDate;
	private TextView pDisplayTimeDeb;
	private TextView pDisplayTimeFin;
	private Button pPickDate;
	private Button pPickTimeDeb;
	private Button pPickTimeFin;
	private int pYear;
	private int pMonth;
	private int pDay;
	private int mHourDeb;
	private int mMinuteDeb;
	private int mHourFin;
	private int mMinuteFin;
	/**
	 * This integer will uniquely define the dialog to be used for displaying
	 * date picker.
	 */
	static final int DATE_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_modif);

		/** Capture our View elements */
		pDisplayDate = (TextView) findViewById(R.id.displayDate);
		pDisplayTimeDeb = (TextView) findViewById(R.id.displayTimeDeb);
		pDisplayTimeFin = (TextView) findViewById(R.id.displayTimeFin);
		pPickDate = (Button) findViewById(R.id.pickDate);
		pPickTimeDeb = (Button) findViewById(R.id.pickTimeDeb);
		pPickTimeFin = (Button) findViewById(R.id.pickTimeFin);
		
		

		/** Listener for click event of the button */
		pPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		pPickTimeDeb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID_HEUREDEB);

			}
		});
		pPickTimeFin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID_HEUREFIN);

			}
		});
		/** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		mHourDeb = cal.get(Calendar.HOUR_OF_DAY);
		mMinuteDeb = cal.get(Calendar.MINUTE);
		mHourFin = cal.get(Calendar.HOUR_OF_DAY);
		mMinuteFin = cal.get(Calendar.MINUTE);
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
	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;
			updateDisplay();
			//displayToast();
		}
	};

	/** Updates the date in the TextView */
	private void updateDisplay() {
		pDisplayDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(pDay).append("/").append(pMonth + 1).append("/")
				.append(pYear).append(" "));
		pDisplayTimeDeb.setText(new StringBuilder()
				.append(mHourDeb).append(":").append(mMinuteDeb).append(" "));
		pDisplayTimeFin.setText(new StringBuilder()
				.append(mHourFin).append(":").append(mMinuteFin).append(" "));
			}

	/** Displays a notification when the date is updated */
/**
  	private void displayToast() {

		Toast.makeText(
				this,
				new StringBuilder().append("La date choisie est : ").append(
						pDisplayDate.getText()), Toast.LENGTH_SHORT).show();
		Toast.makeText(
				this,
				new StringBuilder().append("L'heure choisie est : ").append(
						pDisplayTime.getText()), Toast.LENGTH_SHORT).show();

	}*/

	/** Create a new dialog for date picker */
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, pDateSetListener, pYear, pMonth,
					pDay);
		case TIME_DIALOG_ID_HEUREDEB:
			return new TimePickerDialog(this, mTimeSetListenerHeureDeb, mHourDeb, mMinuteDeb,
					true);
		
		case TIME_DIALOG_ID_HEUREFIN:
		return new TimePickerDialog(this, mTimeSetListenerHeureFin, mHourFin, mMinuteFin,
				true);
		}
		return null;
	}

	static final int TIME_DIALOG_ID_HEUREDEB = 1;

	private TimePickerDialog.OnTimeSetListener mTimeSetListenerHeureDeb = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHourDeb = hourOfDay;
			mMinuteDeb = minute;
			updateDisplay();
		}
	};
	static final int TIME_DIALOG_ID_HEUREFIN = 2;

	private TimePickerDialog.OnTimeSetListener mTimeSetListenerHeureFin = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHourFin = hourOfDay;
			mMinuteFin = minute;
			updateDisplay();
		}
	};


}
