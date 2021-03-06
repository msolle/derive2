package edu.unca.derive;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class DeriveListFragment extends ListFragment {
	private ArrayList<Derive> mDerives;
	private boolean mSubtitleVisible;
	private static final String TAG = "DeriveListFragment";
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

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.derives_title);
		mDerives = DeriveList.get(getActivity()).getDerives();
		setRetainInstance(true);
		mSubtitleVisible = false;

		DeriveAdapter adapter = new DeriveAdapter(mDerives);
		setListAdapter(adapter);
	}


	//Responding to menu selection:
	@TargetApi(11)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_item_new_derive:
			Derive derive = new Derive();
			DeriveList.get(getActivity()).addDerive(derive);
			Intent i = new Intent(getActivity(), DerivePagerActivity.class);
			i.putExtra(DeriveFragment.EXTRA_DERIVE_ID, derive.getId());
			startActivityForResult(i, 0);
			return true;
		case R.id.menu_item_show_subtitle:
			if(getActivity().getActionBar().getSubtitle() == null) {
				getActivity().getActionBar().setSubtitle(R.string.subtitle);
				mSubtitleVisible = true;
				item.setTitle(R.string.hide_subtitle);
			} else {
				getActivity().getActionBar().setSubtitle(null);
				mSubtitleVisible = false;
				item.setTitle(R.string.show_subtitle);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		// Check version issues and enable return button
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					if (NavUtils.getParentActivityIntent(getActivity()) != null) {
						getActivity().getActionBar().setSubtitle(R.string.subtitle);
					}
				}

				ListView listview = (ListView)v.findViewById(android.R.id.list);
				registerForContextMenu(listview);
				return v;
	}

	//Removing items
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		int position = info.position;
		DeriveAdapter adapter = (DeriveAdapter)getListAdapter();
		Derive derive = adapter.getItem(position);
		switch(item.getItemId()) {
		case R.id.menu_item_delete_derive:
			DeriveList.get(getActivity()).deleteDerive(derive);
			adapter.notifyDataSetChanged();
			return true;
		}
		return super.onContextItemSelected(item);
	}


	//ContextMenu
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.derive_list_item_context, menu);
	}

	//Menu
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.derive_fragment_list, menu);
		MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
		if(mSubtitleVisible && showSubtitle != null) {
			showSubtitle.setTitle(R.string.hide_subtitle);
		}
	}




	public void onListItemClick(ListView l, View v, int position, long id) {
		//Get derive from adapter
		Derive d = ((DeriveAdapter)getListAdapter()).getItem(position);
		//Log.d(TAG, d.getTitle() + " was clicked");

		//Start DerivePagerActivity
		Intent i = new Intent(getActivity(), DerivePagerActivity.class);
		i.putExtra(DeriveFragment.EXTRA_DERIVE_ID, d.getId());
		startActivity(i);

	}

	public void onResume() {
		super.onResume();
		((DeriveAdapter)getListAdapter()).notifyDataSetChanged();
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


			//Set Derive
			TextView deriveTextView = (TextView)convertView.findViewById(R.id.derive_list_item_deriveTextView);
			int i = d.getIndex();
			int statement = mStatementBank[i].getStatement();
			deriveTextView.setText(statement);

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