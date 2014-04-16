package edu.unca.derive;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

/*
 * Singleton Object
 */

public class DeriveList {
	private static final String TAG = "DeriveList";
	private static final String FILENAME = "derives.json";
	private DeriveJSONSerializer mSerializer;

	private ArrayList<Derive> mDerives;
	private static DeriveList sDeriveList;
	private Context mAppContext;

	//Add derive to ArrayList<Derive> mDerives
	public void addDerive(Derive d) {
		mDerives.add(d);
	}//addDerive

	//Remove derive
	public void deleteDerive(Derive d) {
		mDerives.remove(d);
	}

	//Private constructor
	private DeriveList(Context appContext) {
		mAppContext = appContext;
		mSerializer = new DeriveJSONSerializer(mAppContext, FILENAME);
		Log.e(TAG, "Calling JSON");
		try {
			mDerives = mSerializer.loadDerives();
			Log.e(TAG, "Load successful");
		} catch(Exception e) {
			mDerives = new ArrayList<Derive>();
			Log.e(TAG, "Error loading derives", e);
		}
	}

	public boolean saveDerives() {
		try {
			mSerializer.saveDerives(mDerives);
			Log.d(TAG, "Derives saved to file");
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Error saving derives: ", e);
			return false;
		}
	}

	/**
	 * Creates singleton if not already existing, else returns singleton
	 * @param c allows singleton to start activities, access resources, find storage, etc.
	 * @return DeriveList returns singleton of DeriveList
	 */
	public static DeriveList get(Context c) {
		if(sDeriveList == null) {
			sDeriveList = new DeriveList(c.getApplicationContext()); //Application Context global to app
		}
		return sDeriveList;
	}

	/**
	 * Getter for mDerives
	 * @return ArrayList of type Derive
	 */
	public ArrayList<Derive> getDerives() {
		return mDerives;
	}

	//get individual Derive by UUID
	public Derive getDerive(UUID id) {
		for(Derive c : mDerives) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}

}