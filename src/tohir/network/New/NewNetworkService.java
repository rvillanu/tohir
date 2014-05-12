package tohir.network.New;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

import javax.servlet.http.Part;

public class NewNetworkService {
	public String createNewNetwork(String networkName, String username, String visibility) {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Network WHERE network_name = '" + networkName + "' AND creator = '" + username + "'");
			if (rs.isBeforeFirst()) {
				rs.close();
				stmt.close();
				con.close();
				return "You already have created a network with this name. Please choose another.";
			}
			else {
				PreparedStatement pstmt = null;
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("INSERT INTO Network VALUES (?, ?, ?)");
				pstmt.setString(1, username);
				pstmt.setString(2, networkName);
				if (visibility == null) {
					pstmt.setString(3, "public");
				}
				else {
					pstmt.setString(3, "private");
				}
				int rowCount = pstmt.executeUpdate();
				
				con.commit();
				con.setAutoCommit(true);
				pstmt.close();
				con.close();
				return "OK";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		
	}

}
