<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Explore</title>
</head>
<body>
	<%try {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
				 									"sa", "jose");
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		if (request.getParameter("searchTerm").isEmpty()) {
			rs = stmt.executeQuery("SELECT DISTINCT network_name, creator FROM Network");
			%>
			<table border="1">
				<tr>
					<th>Network Name</th>
					<th>Creator</th>
					<th>Action</th>
				</tr>
			<%
			while (rs.next()) {
				%>
				<tr>
					<td><%=rs.getString("network_name")%></td>
					<td><%=rs.getString("creator")%></td>
				</tr>
				<%
			}
			%>
			</table>
			<%
		}
		else {
			
			
		}
	} catch (SQLException sqle) {
		System.out.println(sqle.getMessage());
	}
	%>
</body>
</html>