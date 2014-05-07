<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Networks</title>
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
		<a href="home.jsp">home</a>
		<h3>Networks</h3>
		<%
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT network_name FROM Network WHERE creator = '" + session.getAttribute("username") + "'");
			%>
			<form action="yourNetworks.jsp">
			<select name="network_name">
			<%
			while(rs.next()) {
				//System.out.println(rs.getString("network_name"));
				%>
				<option value="<%=rs.getString("network_name")%>"><%=rs.getString("network_name")%></option>
				<%
			}
			rs.close();
			%>
			</select>
			<input type="submit" name="action" value="Select">
			</form>
			<%
			String action = request.getParameter("action");
			String username = (String) session.getAttribute("username");
			String network_name = request.getParameter("network_name");
			if (action != null && action.equals("Select")) {
				System.out.println("User wants to see network: " + network_name);
				rs = stmt.executeQuery("SELECT proteinA_id, proteinB_id FROM Network WHERE creator = '" + username + "' " + 
									 "AND network_name = '" + network_name + "'");
				%>
				<table border="1">
					<tr>
						<th><%=network_name%></th>
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
				<%
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	%>
</body>
</html>