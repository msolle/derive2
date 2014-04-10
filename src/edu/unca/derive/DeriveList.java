package edu.unca.derive;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

/*
 * Singleton Object
 */

public class DeriveList {
	private ArrayList<Derive> mDerives;
	private static DeriveList sDeriveList;
	private Context mAppContext;
	
	//Add derive to ArrayList<Derive> mDerives
	public void addDerive(Derive d) {
		mDerives.add(d);
	}//addDerive
	
	//Private constructor
	private DeriveList(Context appContext) {
		mAppContext = appContext;
		mDerives = new ArrayList<Derive>();
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
