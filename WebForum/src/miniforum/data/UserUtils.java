package miniforum.data;

import javax.servlet.http.HttpSession;

import miniforum.IConstants;

public class UserUtils {
	
	public static String getCurrentUser(HttpSession aSession) {
		String currentUser = (String)
				aSession.getAttribute(IConstants.CURRENT_USER);
		return currentUser;
	}
	
	public static void setCurrentUser(HttpSession aSession,
			String aUserName) {
		aSession.setAttribute(
				IConstants.CURRENT_USER, aUserName);
	}
}
