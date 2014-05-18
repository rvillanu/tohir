<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.google.common.collect.*" import="tohir.dto.Contribution" import="java.util.Collection" import="java.util.Map"%>
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
	if (request.getParameter("batchKey") == null) {
		%>
		<a href="home.jsp">Please choose a network to edit</a>
		<%
	}
	else {
		String batchKey = request.getParameter("batchKey");
		String[] networkInfo = batchKey.split(",");
		String network_creator = networkInfo[0];
		String network_name = networkInfo[1];
		%>
		<h3>Hello, <%= session.getAttribute("username") %></h3>
		You are trying to contribute to the network <b><%=network_name%></b> created by <b><%=network_creator%></b>.<br>
		You want to...<br>
		<%
		Multimap<String, Contribution> contributionBatch = (Multimap<String, Contribution>) session.getAttribute("contributionBatch");
		Map<String, Collection<Contribution>> map = contributionBatch.asMap();
		for (String key : map.keySet()) {
			for (Contribution dml : map.get(key)) {
				%>
				<h3><b><%=dml.simpleToString() %></b></h3>
				<%
			}
		}
		%>
		Send a message to <b><%=network_creator%></b>.<br>
		
		<form id="contribution" action="ContributionServlet" method="post">
			<input type="hidden" name="batchKey" value="<%=batchKey %>">
			<textarea form="contribution" name="message" required></textarea>
			<input type="submit" value="Submit Contribution (request if not a collaborator or the creator)">
		</form>
		
		
		
		<%	
	}
}
	%>
</body>
</html>