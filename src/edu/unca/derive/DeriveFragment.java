/**
 * @author Matthew P. Solle
 */




package edu.unca.derive;


import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;


public class DeriveFragment extends Fragment {	
	
	public static final String EXTRA_DERIVE_ID = "edu.unca.derive.derive_id";
	private static final String DIALOG_DATE = "date";
	private static final String KEY_INDEX = "index"; 
	private static final String TAG = "Derive";
	private static final int REQUEST_DATE = 0;
	
	private Derive mDerive;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mDoneCheckBox;
	private TextView mStatementTextView;
	
	private Statements[] mStatementBank = new Statements[] {
			new Statements(R.string.statementBlocksForward),
			new Statements(R.string.statementBlocksRight),
			new Statements(R.string.statementBlocksBack),
			new Statements(R.string.statementBlocksLeft),
			new Statements(R.string.statementClimbForward),
			new Statements(R.string.statementClimbRight),
			new Statements(R.string.statementClimbBack),
			new Statements(R.string.statementClimbLeft),
			new Statements(R.string.statementFollowShirt),
			new Statements(R.string.statementFollowPants),
			new Statements(R.string.statementFollowHat),
			new Statements(R.string.statementFollowShorts)};
	
	private static int mCurrentIndex;
	private int maxLength = mStatementBank.length - 1;
	
	// Refresh Question
		private void updateQuestion() {
			int statement = mStatementBank[mCurrentIndex].getStatement();
			String revisedStatement = this.getString(statement);
			// Substituting values out for variable information			
			mStatementTextView.setText(revisedStatement);

		}
	
	public static DeriveFragment newInstance(UUID deriveId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DERIVE_ID, deriveId);
		//args.putSerializable(EXTRA_DERIVE_ID, mCurrentIndex);
		DeriveFragment fragment = new DeriveFragment();
		fragment.setArguments(args);
//		args.putSerializable(TAG, mCurrentIndex);
		return fragment;
	}
	
	
	//Fragment.onCreate(Bundle)deriveId
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		UUID deriveId = (UUID)getArguments().getSerializable(EXTRA_DERIVE_ID);
		mDerive = DeriveList.get(getActivity()).getDerive(deriveId);			
		//mDerive = new Derive();
	}//onCreate(Bundle)
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		//savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}
	
	//Abstraction for date update
	public void updateDate() {
		mDateButton.setText(DateFormat.format("K:mm a, EEEE, MMM dd, yyyy", mDerive.getDate()).toString());
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK) return;
		if(requestCode == REQUEST_DATE){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mDerive.setDate(date);
			updateDate();
		}
	}
	
	
	
	/**
	 * @return v Fragment view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_derive, parent, false);
		mCurrentIndex = mDerive.getIndex();
		Log.i(TAG, "index: " + mCurrentIndex);
	    //mCurrentIndex = DeriveList.get(getActivity()).getDerive(deriveId).getIndex();	    
		
		// Array of statements
		mStatementTextView = (TextView)v.findViewById(R.id.statement_text_view);
		//mStatementTextView.setTextSize(fontSize);

		//Get question
		updateQuestion();
		
		//Editable Title Field		
		mTitleField = (EditText) v.findViewById(R.id.derive_title);
		mTitleField.setText(mDerive.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mDerive.setTitle(c.toString());
			}//onTextChanged
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				//Intentionally Blank
			}//beforeTextChanged
			
			public void afterTextChanged(Editable C) {
				//Intentionally Blank
			}//afterTextChanged			
		
		});//mTitleField.addTextChangedListener
		
		//Date button
		mDateButton = (Button)v.findViewById(R.id.derive_date);
		updateDate();
		mDateButton.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mDerive.getDate());
				dialog.setTargetFragment(DeriveFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
				
			}
		});
		
		//Done Check Box
		mDoneCheckBox = (CheckBox)v.findViewById(R.id.derive_done);			
		mDoneCheckBox.setChecked(mDerive.isDone());
		mDoneCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//Set the Derive's done property
				mDerive.setDone(isChecked);
			}//onCheckedChange(CompoundButton, boolean)
		});//check box listener
		
		return v;
		
	}//onCreateView
}//DeriveFragment
