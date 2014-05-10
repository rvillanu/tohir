<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Explore</title>
</head>
<body>
	<form action="explore.jsp">
		Explore networks: <input type="text" name="searchTerm">
		<input type="submit" name="action" value="Explore">
	</form>
	<%try {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
				 									"sa", "jose");
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		if (request.getParameter("searchTerm") != null) {
			if (request.getParameter("searchTerm").isEmpty()) {
				rs = stmt.executeQuery("SELECT DISTINCT network_name, creator, visibility FROM Network");
				%>
				<table border="1">
					<tr>
						<th>Network Name</th>
						<th>Creator</th>
						<th>Action</th>
					</tr>
					<form>
				<%
				while (rs.next()) {
					if (rs.getString("visibility").equals("public")) {
						%>
						<tr>
							<td>
								<input type="text" value="<%=rs.getString("network_name")%>" readonly>
							</td>
							<td>
								<input type="text" value="<%=rs.getString("creator")%>" readonly>
							</td>
							<td>
								<input type="submit" value="View">
							</td>
						</tr>
						<%
					}
				}
				%>
					</form>
				</table>
				<%
			}
			else {
				// searchTerm is not empty. User wants to search something. What should you return?
				
			}
		}
	} catch (SQLException sqle) {
		System.out.println(sqle.getMessage());
	}
	%>
</body>
</html>