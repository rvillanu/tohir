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
	String username = null;
	if (request.getParameter("from").equals("yourNetworks")) {
		username = (String) session.getAttribute("username");
	}
	else if (request.getParameter("from").equals("explore")) {
		username = (String) request.getParameter("creator");
	}
	String network_name = request.getParameter("network_name");
	if (session.getAttribute("username") == null) {
	%>
		<a href="login.jsp">Please login first.</a>
	<% 
	}
	else {
		System.out.println("network.jsp: username = " + username);
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
				ResultSet rs = stmt.executeQuery("SELECT proteinA_id, proteinB_id FROM NetworkEdges WHERE network_creator = '" + username + "' " + 
										 "AND network_name = '" + network_name + "'");
			%>
				<table border="1">
					<tr>
						<th>Network: <%=network_name%></th>
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
				%>
				</table>
		Please upload your network data in CSV formatted txt file. Each line should contain a single binary interaction (ie: HGNC ID of Protein A,HGNC ID of Protein B)<br>
		Go to <a href="http://www.genenames.org/cgi-bin/download?col=gd_hgnc_id&col=gd_app_sym&col=gd_app_name&col=gd_status&col=gd_prev_sym&col=gd_aliases&col=gd_pub_chrom_map&col=gd_pub_acc_ids&col=gd_pub_refseq_ids&status=Approved&status=Entry+Withdrawn&status_opt=2&where=&order_by=gd_hgnc_id&format=text&limit=&hgnc_dbtag=on&submit=submit">HGNC</a> to find the HGNC ID of your protein.<br>
		<input type="file" name="networkFile"><br>
</body>
</html>