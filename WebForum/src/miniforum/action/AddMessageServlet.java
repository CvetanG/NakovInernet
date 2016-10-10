package miniforum.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniforum.IConstants;
import miniforum.data.Message;
import miniforum.data.MessageUtils;
import miniforum.data.UserUtils;

public class AddMessageServlet extends HttpServlet  {
	protected void doPost(HttpServletRequest aRequest,
			HttpServletResponse aResponse)
					throws ServletException, IOException {
		aRequest.setCharacterEncoding("cp1251");
		HttpSession session = aRequest.getSession();
		ServletContext app = session.getServletContext();
		String currentUser = UserUtils.getCurrentUser(session);
		Message msg = new Message();
		msg.setUser(currentUser);
		String subject = aRequest.getParameter(
				IConstants.SUBJECT_PARAM);
		msg.setSubject(subject);
		String contents = aRequest.getParameter(
				IConstants.CONTENTS_PARAM);
		msg.setContents(contents);
		try {
			MessageUtils.addForumMessage(app, msg);
		} catch (IllegalArgumentException iae) {
			session.setAttribute(IConstants.LAST_ERROR,
					"Невалидно заглавие/съдържание на съобщение!");
		}
		aResponse.sendRedirect("main.jsp");
	}
}
