package edu.unca.derive;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = "edu.unca.derive.derive.date";
	private Date mDate;
	
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		
		// M/D/Y Calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(mDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		
		DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				//Change calendar to date object
				mDate = new GregorianCalendar(year,month,day).getTime();
				//In case phone state gets interrupted
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.date_picker_title)
			.setPositiveButton(android.R.string.ok, null)
			.create();
	}
}
