package miniforum.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniforum.IConstants;

public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest aRequest,
			HttpServletResponse aResponse)
					throws ServletException, IOException {
		HttpSession session = aRequest.getSession();
		session.invalidate();
		aResponse.sendRedirect(IConstants.LOGIN_FORM);
	}
}
