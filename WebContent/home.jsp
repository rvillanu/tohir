<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<% 
	if (session.getAttribute("username") == null) {
	%>
		<a href="login.jsp">Please login first.</a>
	<% 
	}
	else {
	%>
		<h3>Hi, <%=session.getAttribute("username")%>!</h3>
		<form action="LogoutServlet">
			<input type="submit" value="logout">
		</form>
		<form action="explore.jsp">
			Explore networks: <input type="text" name="searchTerm">
			<input type="submit" name="action" value="Explore">
		</form>
		<a href="yourNetworks.jsp">Your networks</a>
		<a href="new.jsp">Add a new network</a>
		<a href="batches.jsp">Contribution Batch</a>

	<% 
	}
	%>
</body>
</html>