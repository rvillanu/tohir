<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit</title>
</head>
<body>
Hello <%= (String) session.getAttribute("username") %>! NetworkName: <%=request.getParameter("network_name") %> NetworkCreator: <%=request.getParameter("network_creator") %> 

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
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			String creator;
			String network_name;
			if (request.getParameter("network_name") != null && request.getParameter("network_creator") != null) {
				creator = request.getParameter("network_creator");
				network_name = request.getParameter("network_name");
			}
			else {
				creator = (String) request.getAttribute("network_name");
				network_name = (String) request.getAttribute("network_creator");
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT proteinA_id, proteinB_id " +
												"FROM NetworkEdges " +
												"WHERE network_creator = '" + creator + "' " + 
												"AND network_name = '" + network_name + "'"
											);
			%>
			<table border="1">
				<tr><th>Network name: <%=network_name%> | Creator: <%=creator %></th></tr>
				<tr>
					<th>Protein A</th>
					<th>Protein B</th>
					<th>Action</th>
				</tr>
				<tr>
					<form action="EditNetworkServlet" method="post">
						<input type="hidden" name="action" value="insert">
						<input type="hidden" name="network_name" value="<%=network_name%>">
						<input type="hidden" name="network_creator" value="<%=creator %>">
						<th><input value="" name="newProteinA"></th>
						<th><input value="" name="newProteinB"></th>
						<th><input type="submit" value="Insert"></th>
					</form>
				<%
				while (rs.next()) {
					%>
					<tr>
						<form action="EditNetworkServlet" method="post">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="network_name" value="<%=network_name%>">
							<input type="hidden" name="network_creator" value="<%=creator %>">
							<td>
								<input value="<%=rs.getInt("proteinA_id") %>" name="proteinA">
							</td>
							<td>
								<input value="<%=rs.getInt("proteinB_id") %>" name="proteinB">
							</td>
							<td>
								<input type="submit" value="Delete">
							</td>
						</form>
					</tr>
						<%
				}
				%>
			</table>
			<form action="EditNetworkServlet" method="post">
				If you'd like to insert multiple interactions, please upload a CSV file. Each line should contain a single binary interaction (ie: HGNC ID of Protein A,HGNC ID of Protein B). Go to <a href="http://www.genenames.org/cgi-bin/download?col=gd_hgnc_id&col=gd_app_sym&col=gd_app_name&col=gd_status&col=gd_prev_sym&col=gd_aliases&col=gd_pub_chrom_map&col=gd_pub_acc_ids&col=gd_pub_refseq_ids&status=Approved&status=Entry+Withdrawn&status_opt=2&where=&order_by=gd_hgnc_id&format=text&limit=&hgnc_dbtag=on&submit=submit">HGNC</a> to find the HGNC ID of your protein.<br>
				<input type="file" name="networkFile">
				<input type="submit" value="Upload">
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