<%@ page import="miniforum.IConstants,
				miniforum.HtmlUtils,
				miniforum.data.UserUtils"%>
<%@ page pageEncoding="windows-1251"%>
<table border="0" width="100%">
	<tr>
		<td align="center" bgcolor="#66CCFF">
<%
	String currentUser = UserUtils.getCurrentUser(session);
	if (currentUser != null) {
%>
	����������: <%=HtmlUtils.htmlEscape(currentUser)%>
	 &nbsp;&nbsp;&nbsp;
	<a href="<%=IConstants.LOGOUT_URL%>">[�����]</a>
<%
	 } else {
%>
	 &nbsp;
	 
<%	 
}
%>
		</td>
	</tr>
</table>