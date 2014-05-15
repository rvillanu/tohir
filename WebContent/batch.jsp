<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.google.common.collect.*" import="tohir.dto.Contribution" import="java.util.Collection" import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Batch</title>
</head>
<body>
<%
if (session.getAttribute("username") == null) {
	%>
	<a href="login.jsp">Please login.</a>
<%
}
else {
	%>
	<h3>Your Contribution Batches</h3>
	<%
	Multimap<String, Contribution> contributionBatch = (Multimap<String, Contribution>) session.getAttribute("contributionBatch");
	Map<String, Collection<Contribution>> map = contributionBatch.asMap();
	for (String key : map.keySet()) {
		String[] networkInfo = key.split(","); // networkInfo[0] = network_creator, networkInfo[1] = network_name
		%>
		<table border="1">
			<tr>
				<th>Network creator: <%=networkInfo[0] %></th>
				<th>Network name: <%=networkInfo[1] %></th>
				<th><a href="edit.jsp?network_name=<%=networkInfo[1] %>&network_creator=<%=networkInfo[0] %>">back to this network</a>
			</tr>
			<%
			for (Contribution dml : contributionBatch.get(key)) {
				%>
				<tr>
					<td><%=dml.simpleToString() %></td>
				</tr>
				<%
			}
			%>
			<tr>
				<td>
					<form action="BatchServlet" method="post">
						<input type="hidden" name="batchKey" value="<%=key %>"> 
						<input type="submit" value="Proceed To Contribute">
					</form>
				</td>
			</tr>
		</table>
		<%
	}
}
%>

</body>
</html>