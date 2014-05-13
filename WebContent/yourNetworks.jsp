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
		<h3>Your Networks</h3>
		<table border="1">
			<tr>
				<th>Networks</th>
				<th>Action</th>
			</tr>
		<%
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT network_name FROM Network WHERE creator = '" + session.getAttribute("username") + "'");
			%>
			<tr>
			<form action="network.jsp" method="post">
				<input type=hidden name="from" value="yourNetworks">
				<td>
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
				</td>
				<td>
					<input type="submit" value="View">
				</td>
			</form>
			</tr>
		</table>
		
		<h3>Your Collaborations</h3>
		<table border="1">
			<tr>
				<th>Networks</th>
				<th>Action</th>
			</tr>
			<tr>
			<form action="network.jsp" method="post">
				<input type=hidden name="from" value="yourCollaborations">
				
				<td>
					<select name="network_name">
						<%
						rs = stmt.executeQuery("SELECT network_creator, network_name FROM Collaborators WHERE collaborator = '" + session.getAttribute("username") + "'");
						while (rs.next()) {
							%>
								<option value="<%=rs.getString("network_name")%>"><%=rs.getString("network_name")%></option>
								<input type=hidden name="creator" value="<%=rs.getString("network_creator")%>">
							<%
						}
						%>
					</select>
				</td>
				<td>
					<input type="submit" value="View">
				</td>
			</form>
			</tr>
		</table>	
		<%		
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	%>
</body>
</html>