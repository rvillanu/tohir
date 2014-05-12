package tohir.network.collaborator;

import java.sql.*;

public class NetworkCollaboratorService {

	public boolean addCollaborator(String network_creator, String network_name, String collaborator) {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			con.setAutoCommit(false);
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Collaborators VALUES (?, ?, ?)");
			pstmt.setString(1, network_creator);
			pstmt.setString(2, network_name);
			pstmt.setString(3, collaborator);
			pstmt.executeUpdate();
			con.commit();
			
			con.setAutoCommit(true);
			return true;
		} catch (Exception e) {
			System.out.println("NetworkCollaboratorService: " + e.getMessage());
			return false;
		}
		
	}
	
}
