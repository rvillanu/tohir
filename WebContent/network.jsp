<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Network</title>
</head>
<body>
	<%
	String creator = null;
	if (request.getParameter("from") == null) {
	%>
		<a href="home.jsp">home</a>
		<h3>No network selected.</h3>
	<%
	}
	else {
		boolean ownerIsViewing = false;
		if (request.getParameter("from").equals("yourNetworks")) {
			creator = (String) session.getAttribute("username");
			ownerIsViewing = true;
		}
		else if (request.getParameter("from").equals("explore") || request.getParameter("from").equals("yourCollaborations")) {
			creator = (String) request.getParameter("creator");
		}
		String network_name = request.getParameter("network_name");
		System.out.println("network.jsp: creator = " + creator);
		if (request.getParameter("network_name") == null) {
		%>
			<a href="home.jsp">No network selected</a>
		<% 
		}
		else {
			try {
				DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
				Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
						 									"sa", "jose");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT proteinA_id, proteinB_id FROM NetworkEdges WHERE network_creator = '" + creator + "' " + 
										 "AND network_name = '" + network_name + "'");
				if (ownerIsViewing) {
					%>
					<form action="collaborators.jsp" method="post">
						<input type="hidden" name="network_creator" value="<%=creator %>">
						<input type="hidden" name="network_name" value="<%=network_name%>">
						<input type="submit" value="Collaborators">
					</form>
					<%
				}
			
			%>
				<table border="1">
					<tr>
						<th>Network name: <%=network_name%> | Creator: <%= creator %></th>
					</tr>
					<tr>
						<th>Protein A</th>
						<th>Protein B</th>
					</tr>
					<%
					while (rs.next()) {
					%>
						<tr>
							<td><%= rs.getInt("proteinA_id") %></td>
							<td><%= rs.getInt("proteinB_id") %></td>
						</tr>
					<%
					}
					%>
					</table>
					<form action="edit.jsp">
						<input type="hidden" name="network_name" value="<%=network_name%>">
						<input type="hidden" name="network_creator" value="<%=creator%>">
						<input type="submit" value="edit">
					</form>
					<%
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
				
	}
	%>
</body>
</html>