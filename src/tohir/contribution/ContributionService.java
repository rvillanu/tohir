package tohir.contribution;

import java.sql.*;
import java.util.Collection;

import tohir.dto.Contribution;

public class ContributionService {

	public boolean processContributions(String username, String network_creator, String network_name, Collection<Contribution> contributionDML, String message) {
		// if username is owner or collaborator: 
			// go through all Contributions in contributionDML
			// Perform a batch execution: 
				// All INSERTs, DELETEs, UPDATES on NetworkEdges *AND* document these changes in the table Contribution (status = ACCEPTED)
			// empty batch(batchKey)
		// if username is neither owner or collaborator:
			// go through all Contributions in contributionDML
			// Perform a batch execution: 
				// INSERT these contributions in the Contribution table with status = PENDING
			// empty batch(batchKey)
		
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			Statement stmt = con.createStatement();
			boolean usernameIsACollaborator = false;
			ResultSet rs = stmt.executeQuery("SELECT collaborator FROM Collaborators WHERE network_name = '" + network_name + "' AND network_creator = '" + network_creator + "'");
			while (rs.next()) {
				if (rs.getString("collaborator").equals(username)) {
					usernameIsACollaborator = true;
				}
			}
			if (username.equals(network_creator) || usernameIsACollaborator) {
				String status = "accepted";
				PreparedStatement pstmt;
				con.setAutoCommit(false);
				for (Contribution dml : contributionDML) {
					System.out.println(dml.generateNetworkEdgesDMLStatement());
					System.out.println(dml.generateContributionDMLStatement(status, message));
					
					pstmt = con.prepareStatement(dml.generateNetworkEdgesDMLStatement());
					pstmt.executeUpdate();
					pstmt = con.prepareStatement(dml.generateContributionDMLStatement(status, message));
					pstmt.executeUpdate();
					
				}
				con.commit();
				
				con.setAutoCommit(true);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
