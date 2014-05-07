package tohir.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

import javax.servlet.http.Part;

public class NewNetworkService {
	public String createNewNetwork(String networkName, String username, Part networkFile) {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Network WHERE network_name = '" + networkName + "'");
			if (rs.isBeforeFirst()) {
				rs.close();
				stmt.close();
				con.close();
				return "You already have created a network with this name. Please choose another.";
			}
			else {
				PreparedStatement pstmt = null;
				con.setAutoCommit(false);
				InputStream is = networkFile.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while((line = br.readLine()) != null) {
					String [] nodes = line.split(",");
					/* To maintain data integrity of my db, I insert a PPI like so (HGNC_ID_proteinA, HGNC_ID_proteinB)
					 * HGNC_ID_proteinA < HGNC_ID_proteinB
					 */
					String proteinA_id = nodes[0];
					String proteinB_id = nodes[1];
					if (nodes[0].compareTo(nodes[1]) > 0 ) {
						proteinA_id = nodes[1];
						proteinB_id = nodes[0];
					}
					// Network(creator, network_name, proteinA_id, proteinB_id)
					pstmt = con.prepareStatement("INSERT INTO Network VALUES (?, ?, ?, ?)");
					pstmt.setString(1, username);
					pstmt.setString(2, networkName);
					pstmt.setInt(3, Integer.parseInt(proteinA_id));
					pstmt.setInt(4, Integer.parseInt(proteinB_id));
					int rowCount = pstmt.executeUpdate();
				}
				con.commit();
				con.setAutoCommit(true);
				pstmt.close();
				con.close();
				br.close();
				is.close();
				return "OK";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		
	}

}
