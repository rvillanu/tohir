package tohir.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginService {
	public boolean authenticate(String username, String password) {
		
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			String SELECT_username = "SELECT username " + 
									"FROM Users " +
									"WHERE username = '" + username + "'";
			
			Statement stmt = con.createStatement();
			ResultSet rs_user = stmt.executeQuery(SELECT_username);
			if (!rs_user.isBeforeFirst()) {
				System.out.println("LoginService: No such username exists.");
				// *** Redirect USER to login page here! ***
			}
			else {
				System.out.println("LoginService: OK, found " + username + " in our database");
				String SELECT_password = "SELECT password " +
										"FROM Users " +
										"WHERE username = '" + username + "'";
				ResultSet rs_password = stmt.executeQuery(SELECT_password);
				rs_password.next();
				String correctPassword = rs_password.getString("password");
				System.out.println("LoginService: OK, password should be: '" + correctPassword + "'" );
				
				if (password.equals(correctPassword)) {
					System.out.println("LoginService: Credentials authenticated!");
					return true;
				}
				else {
					System.out.println("Wrong username and/or password.");
					return false;
				}
			}
			
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return false;
	}
	
	public String getUsername(String username) {
		return "";
	}
	
}
