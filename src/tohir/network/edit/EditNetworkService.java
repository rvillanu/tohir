package tohir.network.edit;

import java.sql.*;

public class EditNetworkService {
	public String requestNetworkInsert() {
		// session username is NOT owner or collaborator, then this Insert must be a ContributionRequest
		return "OK";
	}
	
	public String NetworkInsert(String username, String network_creator, String network_name, int newProteinA, int newProteinB) {
		
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			PreparedStatement pstmt = null;
			con.setAutoCommit(false);
			// if session username is owner or collaborator, then go ahead and just insert 
			if (username.equals(network_creator)) {
				pstmt = con.prepareStatement("INSERT INTO NetworkEdges VALUES (?, ?, ?, ?)");
				pstmt.setString(1, username);
				pstmt.setString(2, network_name);
				if (newProteinA < newProteinB) {
					pstmt.setInt(3, newProteinA);
					pstmt.setInt(4, newProteinB);
				}
				else {
					pstmt.setInt(3, newProteinB);
					pstmt.setInt(4, newProteinA);
				}
				pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			}
			else {
				//requestNetworkInsert();
			}
			// else call requestNetworkInsert in here
			return "OK";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		
		
		
	}
	
	public String requestNetworkDelete() {
		return "OK";
	}
	
	public String NetworkDelete() {
		return "OK";
	}
	
}
