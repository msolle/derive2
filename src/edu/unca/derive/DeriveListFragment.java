package edu.unca.derive;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class DeriveListFragment extends ListFragment {
	private ArrayList<Derive> mDerives;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.derives_title);
		mDerives = DeriveList.get(getActivity()).getDerives();
	}

}
