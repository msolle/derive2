package edu.unca.derive;

import java.util.Date;
import java.util.UUID;

public class Derive {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mDone;
	
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
	
	public Derive() {
		//Generate unique identifier
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
}
