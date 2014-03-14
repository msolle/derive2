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
	
	//Private constructor
	private DeriveList(Context appContext) {
		mAppContext = appContext;
		mDerives = new ArrayList<Derive>();
		for(int i = 0; i < 100; i++) {
			Derive d = new Derive();
			d.setTitle("Derive: " + i);
			d.setDone(i % 2 == 0); //Every other
			mDerives.add(d);
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
	
	public Derive getDerive(UUID id) {
		for(Derive c : mDerives) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
}
