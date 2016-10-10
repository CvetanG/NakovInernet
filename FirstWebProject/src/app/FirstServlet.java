package app;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
		PrintWriter out = aResponse.getWriter();
		out.println("<HTML>");
		out.println("The time is: " + new java.util.Date());
		out.println("</HTML>");
	}
}