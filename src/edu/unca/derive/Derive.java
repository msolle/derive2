package edu.unca.derive;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Derive {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mDone;
	private int mIndex;
	private String mNotes;
	
	public String getNotes() {
		return mNotes;
	}

	public void setNotes(String notes) {
		mNotes = notes;
	}

	Random random = new Random();
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}		

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isDone() {
		return mDone;
	}

	public void setDone(boolean done) {
		mDone = done;
	}

	public UUID getId() {
		return mId;
	}
	
	public int getIndex() {
		return mIndex;
	}
	
	public void setIndex(int index) {
		mIndex = index;
	}
	
	public Derive() {
		//Generate unique identifier
		mId = UUID.randomUUID();
		mDate = new Date();
		mIndex = randInt(0, 4);
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
	
	public int randInt(int min, int max) {
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
}
