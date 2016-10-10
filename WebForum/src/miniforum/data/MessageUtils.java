package miniforum.data;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import miniforum.IConstants;

public class MessageUtils {
	public static synchronized Message[] getForumMessages(
			ServletContext aApplication) {
		ArrayList msgs = (ArrayList) aApplication.getAttribute(
				IConstants.FORUM_MESSAGES);
		if (msgs == null) {
			msgs = new ArrayList();
			aApplication.setAttribute(
					IConstants.FORUM_MESSAGES, msgs);
		}
		Message[] copyOfMsgs =
				(Message[]) msgs.toArray(new Message[]{});
		return copyOfMsgs;
	}
	public static synchronized void addForumMessage(
			ServletContext aApplication, Message aMessage) {
		if (aMessage.getSubject() == null ||
				aMessage.getSubject().length() == 0 ||
				aMessage.getContents() == null ||
				aMessage.getContents().length() == 0) {
			throw new IllegalArgumentException("Invalid msg!");
		}
		ArrayList msgs = (ArrayList) aApplication.getAttribute(
				IConstants.FORUM_MESSAGES);
		msgs.add(aMessage);
	}
	public static synchronized void deleteForumMessage(
			long aID, ServletContext aApplication) {
		ArrayList msgs = (ArrayList) aApplication.getAttribute(
				IConstants.FORUM_MESSAGES);
		for (int i=0; i<msgs.size(); i++) {
			Message msg = (Message) msgs.get(i);
			if (msg.getID() == aID) {
				// Message found. Delete it
				msgs.remove(i);
				return;
			}
		}
		throw new IllegalArgumentException("Invalid msg ID!");
	}
}
