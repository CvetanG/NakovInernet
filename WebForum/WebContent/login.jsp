<%@ page import="miniforum.IConstants"%>
<%@ page contentType="text/html;charset=windows-1251" %>

<html>
<head><title>Mini Forum - Login</title></head>
<body>
<%@ include file="header.jsp" %>
<br>
<div align="center">
<form method="post" action="<%=IConstants.LOGIN_URL%>">
<input type="text" name="<%=IConstants.USER_PARAM%>">
<br>
<input type="password"
name="<%=IConstants.PASSWORD_PARAM%>">
<br>
<input type="submit" value="Влез">
</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>