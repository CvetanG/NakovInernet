<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
	private java.util.Random mRandomGenerator = new java.util.Random();
	private int getRandomNumber(int range) {
		return mRandomGenerator.nextInt(range);
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Random numbers demo</title>
</head>

	<body>
		<% for (int i=1; i <= 5; i++) { %>
			Random number #<%= i %> is
			<%= "" + getRandomNumber(1000) %>.
			<br>
		<% } %>
	</body>
	
</html>