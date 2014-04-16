package edu.unca.derive;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Derive {
	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_DONE = "done";
	private static final String JSON_DATE = "date";
	private static final String JSON_INDEX = "index";
	private static final String JSON_NOTES = "notes";
	private static final String JSON_PHOTO = "photo";
	
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mDone;
	private int mIndex;
	private String mNotes;
	private Photo mPhoto;
	
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
		mTitle = "Title: ";
		mNotes = "Notes: ";
	}
	
	public Derive(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		mTitle = json.getString(JSON_TITLE);
		mDate = new Date(json.getString(JSON_DATE));
		mDone = json.getBoolean(JSON_DONE);
		mIndex = json.getInt(JSON_INDEX);
			mNotes = json.getString(JSON_NOTES);
		if(json.has(JSON_PHOTO)) {
			mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
		}
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
	
	public int randInt(int min, int max) {
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_DATE, mDate);
		json.put(JSON_DONE, mDone);
		json.put(JSON_INDEX, mIndex);
		json.put(JSON_NOTES, mNotes);
		if(mPhoto != null) {
			json.put(JSON_PHOTO, mPhoto.toJSON());
		}
		return json;
	}

	public Photo getPhoto() {
		return mPhoto;
	}

	public void setPhoto(Photo photo) {
		mPhoto = photo;
	}
}
