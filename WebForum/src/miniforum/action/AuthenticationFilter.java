package miniforum.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniforum.IConstants;
import miniforum.data.UserUtils;

public class AuthenticationFilter implements Filter {
	public void init(FilterConfig aFilterConfig)
			throws ServletException {
	}
	public void doFilter(ServletRequest aRequest,
			ServletResponse aResponse, FilterChain aFilterChain)
					throws IOException, ServletException {
		HttpServletRequest httpRequest =
				(HttpServletRequest) aRequest;
		HttpServletResponse httpResponse =
				(HttpServletResponse) aResponse;
		String requestedPage = getRequestedPage(httpRequest);
		if (requestedPage.equals(IConstants.EMPTY_URL)) {
			// Accessing root directory redirects to login form
			httpResponse.sendRedirect(IConstants.LOGIN_FORM);
			return;
		}
		if (IConstants.LOGIN_FORM.equals(requestedPage) ||
				IConstants.LOGIN_URL.equals(requestedPage) ||
				IConstants.LOGOUT_URL.equals(requestedPage)) {
			// Accessing login/logout is always permitted
			aFilterChain.doFilter(aRequest, aResponse);
			return;
		}
		HttpSession session = httpRequest.getSession();
		boolean authenticated =
				(UserUtils.getCurrentUser(session) != null);
		if (authenticated) {
			// Authenticated user. Permit the request
			aFilterChain.doFilter(aRequest, aResponse);
		} else {
			// Not authenticated user. Redirect to login form
			session.setAttribute(IConstants.LAST_ERROR,
					"Поисканата страница изисква автентикация. " +
					"Моля първо влезте в системата!");
			session.setAttribute(IConstants.ORIGINAL_URL,
					requestedPage);
			httpResponse.sendRedirect(IConstants.LOGIN_FORM);
		}
	}
	private String getRequestedPage(
			HttpServletRequest aHttpRequest) {
		String url = aHttpRequest.getRequestURI();
		int firstSlash = url.indexOf("/",1);
		String requestedPage = null;
		if (firstSlash != -1) requestedPage =
				url.substring(firstSlash + 1, url.length());
		return requestedPage;
	}
	public void destroy() {

	}
}
