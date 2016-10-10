package miniforum.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniforum.IConstants;
import miniforum.data.MessageUtils;

public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest aRequest,
			HttpServletResponse aResponse)
					throws ServletException, IOException {
		HttpSession session = aRequest.getSession();
		String idParameter = aRequest.getParameter(
				IConstants.ID_PARAM);
		try {
			int id = Integer.parseInt(idParameter);
			ServletContext application =
					session.getServletContext();
			MessageUtils.deleteForumMessage(id, application);
		} catch (Exception ex) {
			session.setAttribute(IConstants.LAST_ERROR,
					"Не мога да изтрия съобщението!");
		}
		aResponse.sendRedirect(IConstants.MAIN_FORM);
	}
}
