<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a New Network</title>
</head>
<body>
	<%
	if (session.getAttribute("username") == null) {
	%>
		<h3>Please <a href="login.jsp">login</a>.</h3>
	<%
	}
	else {
	%>
	<form action="NewNetworkServlet" id="newNetwork" method="POST" enctype="multipart/form-data">
		Network name: <input type="text" name="networkName" required><br>
		<input type="checkbox" name="visibility" value="private">Check to make this network private <br>
		Please upload your network data in CSV formatted txt file. Each line should contain a single binary interaction (ie: HGNC ID of Protein A,HGNC ID of Protein B)<br>
		Go to <a href="http://www.genenames.org/cgi-bin/download?col=gd_hgnc_id&col=gd_app_sym&col=gd_app_name&col=gd_status&col=gd_prev_sym&col=gd_aliases&col=gd_pub_chrom_map&col=gd_pub_acc_ids&col=gd_pub_refseq_ids&status=Approved&status=Entry+Withdrawn&status_opt=2&where=&order_by=gd_hgnc_id&format=text&limit=&hgnc_dbtag=on&submit=submit">HGNC</a> to find the HGNC ID of your protein.<br>
		<input type="file" name="networkFile"><br>
		<input type="submit" value="Add Network">
	</form>
	<%
	}
	%>
	
	
</body>
</html>