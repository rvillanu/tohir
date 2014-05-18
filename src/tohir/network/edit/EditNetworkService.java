package tohir.network.edit;

import java.sql.*;

import tohir.dto.Contribution;

import com.google.common.collect.*;

public class EditNetworkService {
	public String requestNetworkInsert() {
		// session username is NOT owner or collaborator, then this Insert must be a ContributionRequest
		return "Contribution request";
	}
	
	public String batchInsertOrDelete(Multimap<String, Contribution> contributionBatch, String network_creator, String network_name, String username, String action, int newProteinA, int newProteinB) {
		String networkInfo = network_creator + "," + network_name;
		Contribution contrib = new Contribution(network_creator, network_name, username, action, newProteinA, newProteinB);
		contributionBatch.put(networkInfo, contrib);
		return "OK";
	}
	
	public String requestNetworkDelete() {
		return "Contribution request";
	}
	
	public String networkDelete(String username, String network_creator, String network_name, int proteinA, int proteinB) {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			PreparedStatement pstmt = null;
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);
			boolean usernameIsACollaborator = false;
			String sql = "SELECT collaborator " + 
					"FROM Collaborators WHERE network_creator = '" + network_creator + "' " +
					"AND network_name = '" + network_name + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("collaborator").equals(username)) {
					System.out.println("EditNetworkService: ok, you're a collaborator");
					usernameIsACollaborator = true;
				}
			}
			if (username.equals(network_creator) || usernameIsACollaborator) {
				pstmt = con.prepareStatement("DELETE FROM NetworkEdges WHERE network_creator = ? AND network_name = ? AND proteinA_id = ? AND proteinB_id = ?");
				pstmt.setString(1, network_creator);
				pstmt.setString(2, network_name);
				pstmt.setInt(3, proteinA);
				pstmt.setInt(4, proteinB);
				pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			}
			
			else {
				System.out.println("EditNetworkService: sorry, you're not a collaborator. you're gonna have to send a ContributionRequest");
				return "Contribution request";
			}
			
			return "OK";
		} catch (Exception e) {
			System.out.println("EditNetworkService: oops -- " + e.getMessage());
			return e.getMessage();
		}
	}
	
}
