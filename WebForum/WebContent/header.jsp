<%@ page import="miniforum.HtmlUtils,
				 miniforum.IConstants"%>
<%@ page pageEncoding="windows-1251"%>
<%-- Disable browser caching --%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
%>
<table border="0" width="100%">
	<tr>
		<td align="center" bgcolor="#66CCFF">Мини форум - (c) Светлин Наков, 2004</td>
	</tr>
<%
	String errorMsg = (String)
	session.getAttribute(IConstants.LAST_ERROR);
	if (errorMsg != null) {
%>
	<tr>
		<td align="center"><br> <font color="red"><b>
					Грешка: <%=HtmlUtils.htmlEscape(errorMsg)%>
			</b></font> <br></td>
	</tr>
<%
	session.removeAttribute(IConstants.LAST_ERROR);
}
%>
</table>