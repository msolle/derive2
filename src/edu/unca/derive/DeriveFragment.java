/**
 * @author Matthew P. Solle
 */




package edu.unca.derive;


import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;


public class DeriveFragment extends Fragment {	
	
	public static final String EXTRA_DERIVE_ID = "edu.unca.derive.derive_id";
	
	private Derive mDerive;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mDoneCheckBox;
	
	
	public static DeriveFragment newInstance(UUID deriveId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DERIVE_ID, deriveId);
		DeriveFragment fragment = new DeriveFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	
	//Fragment.onCreate(Bundle)
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		UUID deriveId = (UUID)getArguments().getSerializable(EXTRA_DERIVE_ID);
		mDerive = DeriveList.get(getActivity()).getDerive(deriveId);
		//mDerive = new Derive();
	}//onCreate(Bundle)
	
	/**
	 * @return v Fragment view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_derive, parent, false);
		
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
		mDateButton.setText(DateFormat.format("K:mm a, EEEE, MMM dd, yyyy", mDerive.getDate()).toString());
		mDateButton.setEnabled(false);
		
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
