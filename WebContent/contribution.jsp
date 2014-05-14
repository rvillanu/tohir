<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contribution</title>
</head>
<body>
<%
if (session.getAttribute("username") == null) {
	%>
	<a href="login.jsp">Please login.</a>
	<%
}
else {
	if ( (request.getParameter("network_name") == null && request.getParameter("network_creator") == null) && (request.getAttribute("network_name") == null && request.getAttribute("network_creator") == null) ) {
		%>
		<a href="home.jsp">Please choose a network to edit</a>
		<%
	}
	else {
		%>
		<h3>Hello, <%= session.getAttribute("username") %></h3>
		You are trying to contribute to the network <b><%=request.getParameter("network_name") %></b> created by <b><%=request.getParameter("network_creator") %></b>.<br>
		You want to...<br>
		<h3><%=request.getParameter("action") %> (<%=request.getParameter("newProteinA") %>, <%=request.getParameter("newProteinB") %>)</h3>
		Send a message to <b><%=request.getParameter("network_creator") %></b>.<br>
		<textarea form="contribution"></textarea>
		<form id="contribution">
			<input type="submit" value="Send Contribution Request">
		</form>
		
		
		
		<%	
	}
}
	%>
</body>
</html>