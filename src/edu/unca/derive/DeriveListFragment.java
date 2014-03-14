package edu.unca.derive;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeriveListFragment extends ListFragment {
	private ArrayList<Derive> mDerives;
	private static final String TAG = "CrimeListFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.derives_title);
		mDerives = DeriveList.get(getActivity()).getDerives();
		
		ArrayAdapter<Derive> adapter = new ArrayAdapter<Derive>(getActivity(), android.R.layout.simple_list_item_1, mDerives);
		setListAdapter(adapter);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		Derive d = (Derive)(getListAdapter()).getItem(position);
		Log.d(TAG, d.getTitle() + " was clicked");
	}
	
	private class DeriveAdapter extends ArrayAdapter<Derive> {
		
		public DeriveAdapter(ArrayList<Derive> derives) {
			super(getActivity(), 0, derives);
		}
		
	}

}
