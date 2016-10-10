package miniforum.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniforum.IConstants;
import miniforum.data.UserUtils;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest aRequest,
			HttpServletResponse aResponse)
					throws ServletException, IOException {
		aRequest.setCharacterEncoding("cp1251");
		String userName =
				aRequest.getParameter(IConstants.USER_PARAM);
		String password =
				aRequest.getParameter(IConstants.PASSWORD_PARAM);
		HttpSession session = aRequest.getSession();
		if (isUserValid(userName, password)) {
			// User valid. Redirect to the last requested page
			UserUtils.setCurrentUser(session, userName);
			String targetURL = (String)
					session.getAttribute(IConstants.ORIGINAL_URL);
			session.removeAttribute(IConstants.ORIGINAL_URL);
			if (targetURL == null) {
				targetURL = IConstants.MAIN_FORM;
			}
			aResponse.sendRedirect(targetURL);
		} else if (userName != null) {
			// User invalid. Redirect to login form
			session.setAttribute(IConstants.LAST_ERROR,
					"Невалиден потребител или парола!");
			aResponse.sendRedirect(IConstants.LOGIN_FORM);
		}
	}
	public static boolean isUserValid(String aUserName,
			String aPassword) {
		boolean valid =
				(aUserName != null) && (aPassword != null) &&
				(aUserName.length() > 0) &&
				(aUserName.equals(aPassword));
		return valid;
	}
}
