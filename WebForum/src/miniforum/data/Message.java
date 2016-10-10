package miniforum.data;

public class Message {
	private long mID;
	private String mUser;
	private String mSubject;

	private String mContents;
	private static long mSequenceNumber = 0;
	public Message() {
		// Assign an unique ID to the newly create message
		synchronized (Message.class) {
			mSequenceNumber++;
			mID = mSequenceNumber;
		}
	}
	public long getID() {
		return mID;
	}
	public String getUser() {
		return mUser;
	}
	public void setUser(String aUser) {
		mUser = aUser;
	}
	public String getSubject() {
		return mSubject;
	}
	public void setSubject(String subject) {
		mSubject = subject;
	}
	public String getContents() {
		return mContents;
	}
	public void setContents(String contents) {
		mContents = contents;
	}
}
