package edu.unca.derive;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class DeriveListFragment extends ListFragment {
	private ArrayList<Derive> mDerives;
	private static final String TAG = "CrimeListFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.derives_title);
		mDerives = DeriveList.get(getActivity()).getDerives();
		
		DeriveAdapter adapter = new DeriveAdapter(mDerives);
		setListAdapter(adapter);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		Derive d = ((DeriveAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, d.getTitle() + " was clicked");
	}
	
	private class DeriveAdapter extends ArrayAdapter<Derive> {
		
		public DeriveAdapter(ArrayList<Derive> derives) {
			super(getActivity(), 0, derives);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Inflate view in none passed
			if(convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_derive, null);				
			}
			
			//Configure for Derive
			Derive d = getItem(position);
			
			//Set Title
			TextView titleTextView = (TextView)convertView.findViewById(R.id.derive_list_item_titleTextView);
			titleTextView.setText(d.getTitle());
			
			//Set Date
			TextView dateTextView = (TextView)convertView.findViewById(R.id.derive_list_item_dateTextView);
			dateTextView.setText(d.getDate().toString());
			
			//Set check box
			CheckBox doneCheckBox = (CheckBox)convertView.findViewById(R.id.derive_list_item_doneCheckBox);
			doneCheckBox.setChecked(d.isDone());
			
			return convertView;
		}
		
	}

}
