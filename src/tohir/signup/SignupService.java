package tohir.signup;

import java.sql.*;

public class SignupService {

	/* return 0 if the User typed non-matching passwords 
	 * return -2 if the provided username already exists
	 * return -1 if the provided email is already registered to another User
	 * return 1 upon successful registration
	 */
	public int registerNewUser(String username, String firstName, String lastName, String password, String passwordConfirm, String email) {
		try {
			if (!(password.equals(passwordConfirm))) {
				return 0;
			}
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bimm185",
					 									"sa", "jose");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
			pstmt.setString(1, username);		
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, password);
			pstmt.setString(5, email);
			
			int rowCount = pstmt.executeUpdate();
			return 1;
			
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
			return -3;
		}
		
		//return 0;
		
	}
}
