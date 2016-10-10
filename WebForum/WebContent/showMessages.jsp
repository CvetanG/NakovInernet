<%@ page
	import="miniforum.data.Message,
miniforum.HtmlUtils,
java.util.Vector,
miniforum.data.MessageUtils,
java.util.Enumeration"%>
<%@ page pageEncoding="windows-1251"%>
<%
Message[] messages =
MessageUtils.getForumMessages(application);
if (messages.length == 0) {
%>
<br>
<b>Няма съобщения.</b>
<%
} else {
for (int i=0; i<messages.length; i++) {
Message msg = messages[i];
long id = msg.getID();
String author = msg.getUser();
String subject = msg.getSubject();
String contents = msg.getContents();
%>
<br>
<table border="1" width="90%" cellspacing="0" cellpadding="5">
	<tr>
		<td bgcolor="lightblue"><i>Автор:</i> <%=HtmlUtils.htmlEscape(author)%>
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <i>Тема:</i> <%=HtmlUtils.htmlEscape(subject)%>
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <a href="doDelete?id=<%=id%>">
				[изтрий] </a></td>
	</tr>
	<tr>
		<td colspan="3"><font face="monospace"> <%=HtmlUtils.htmlEscape(contents)%>
		</font></td>
	</tr>
</table>
<%
		}
	}
%>