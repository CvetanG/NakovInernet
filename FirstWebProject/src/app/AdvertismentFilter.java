package app;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class AdvertismentFilter implements Filter {

	public void init(FilterConfig aFilterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest aRequest, ServletResponse aResponse, FilterChain aFilterChain)
			throws IOException, ServletException {
		PrintWriter responseOutput = aResponse.getWriter();
		HttpServletResponse httpResponse = (HttpServletResponse) aResponse;
		MemoryResponseWrapper responseWrapper = new MemoryResponseWrapper(httpResponse);
		aFilterChain.doFilter(aRequest, responseWrapper);
		String contentType = responseWrapper.getContentType();
		String originalResp = responseWrapper.toString();
		if ((contentType != null) && contentType.toLowerCase().startsWith("text/html")) {
			String newResponse = addAdvertisment(originalResp);
			responseOutput.print(newResponse);
		} else {
			responseOutput.println(originalResp);
		}
		responseOutput.close();
	}

	private String addAdvertisment(String aHtmlText) {
		int endOfBodyIndex = aHtmlText.toLowerCase().indexOf("</body>");
		String htmlBefore;
		String htmlAfter;
		if (endOfBodyIndex != -1) {
			htmlBefore = aHtmlText.substring(0, endOfBodyIndex - 1);
			htmlAfter = aHtmlText.substring(endOfBodyIndex);
		} else {
			htmlBefore = aHtmlText;
			htmlAfter = "";
		}
		String result = htmlBefore + "\n\n<br><p align='center'>" + "<a href='http://www.devbg.org'><img border='0' "
				+ "src='http://www.devbg.org/site/wp-content/uploads/2015/08/Logo-BARS.png'>" + "</a></p>\n\n" + htmlAfter;
		return result;
	}

	public void destroy() {
	}
}

class MemoryResponseWrapper extends HttpServletResponseWrapper {
	private CharArrayWriter mOutput;

	public MemoryResponseWrapper(HttpServletResponse aResponse) {
		super(aResponse);
		mOutput = new CharArrayWriter();
	}

	public String toString() {
		String result = mOutput.toString();
		return result;
	}

	public PrintWriter getWriter() {
		PrintWriter printWriter = new PrintWriter(mOutput);
		return printWriter;
	}
}
