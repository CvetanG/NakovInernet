<%@ page import="java.util.Vector,
				miniforum.HtmlUtils"%>
<%@ page contentType="text/html;charset=windows-1251" %>
<html>
<head><title>Mini Forum - Messages</title></head>
<body>
<%@  include file="header.jsp" %>
<div align="center">
<%-- Display all messages --%>
<%@ include file="showMessages.jsp" %>
<br>
<%-- Display add new message form --%>
<%@ include file="addNewMessageForm.jsp" %>
</div>
<%@  include file="footer.jsp" %>
</body>
</html>