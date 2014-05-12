<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collaborators</title>
</head>
<body>
	<% 
	if (session.getAttribute("username") == null) {
		%>
		<a href="login.jsp">Please login</a>
		<%
	}
	else {
		%>
		Hello, <%=session.getAttribute("username") %>
		<%
		if (request.getParameter("network_name") == null || request.getParameter("network_creator") == null) {
			%>
			<a href="home.jsp">Please choose a network to edit</a>
			<%
		}
		else {
			%>
			<h3> Network Name: <%=request.getParameter("network_name") %> Network creator: <%=request.getParameter("network_creator") %></h3>
			<table border="1">
				<tr><th>Collaborator</th></tr>
				<tr>
					<form action="NetworkCollaboratorServlet" method="post">
						<input type="hidden" name="network_creator" value="<%=request.getParameter("network_creator") %>">
						<input type="hidden" name="network_name" value="<%=request.getParameter("network_name") %>">
						<td>
							<input type="text" value="" name="collaborator">
						</td>
						<td>
							<input type="submit" value="Add">
						</td>
					</form>
				</tr>
			</table>
			<%
			
		}
		
	}
	%>
</body>
</html>