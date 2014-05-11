<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a New Network</title>
</head>
<body>
	<%
	if (session.getAttribute("username") == null) {
	%>
		<h3>Please <a href="login.jsp">login</a>.</h3>
	<%
	}
	else {
	%>
	<form action="NewNetworkServlet" id="newNetwork" method="POST" enctype="multipart/form-data">
		Network name: <input type="text" name="networkName" required><br>
		<input type="checkbox" name="visibility" value="private">Check to make this network private <br>
		<input type="submit" value="Add Network">
	</form>
	<%
	}
	%>
	
	
</body>
</html>