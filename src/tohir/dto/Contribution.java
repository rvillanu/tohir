package tohir.dto;

public class Contribution {
	private String networkCreator;
	private String networkName;
	private String requester;
	private String action;
	private int oldProteinA;
	private int oldProteinB;
	private int newProteinA;
	private int newProteinB;
	
	// Contribution is an INSERT or DELETE to an existing network
	public Contribution(String networkCreator, String networkName, String requester, String action, int newProteinA, int newProteinB) {
		this.networkCreator = networkCreator;
		this.networkName = networkName;
		this.requester = requester;
		this.action = action;
		if (newProteinA < newProteinB) {
			this.newProteinA = newProteinA;
			this.newProteinB = newProteinB;
		}
		else {
			this.newProteinA = newProteinB;
			this.newProteinB = newProteinA;
		}
	}
	
	// Contribution is an UPDATE to an existing network
	public Contribution(String networkCreator, String networkName, String requester, String action, int newProteinA, int newProteinB, int oldProteinA, int oldProteinB) {
		this.networkCreator = networkCreator;
		this.networkName = networkName;
		this.requester = requester;
		this.action = action;
		if (newProteinA < newProteinB) {
			this.newProteinA = newProteinA;
			this.newProteinB = newProteinB;
		}
		else {
			this.newProteinA = newProteinB;
			this.newProteinB = newProteinA;
		}
		if (oldProteinA < oldProteinB) {
			this.oldProteinA = oldProteinA;
			this.oldProteinB = oldProteinB;
		}
		else {
			this.oldProteinA = oldProteinB;
			this.oldProteinB = oldProteinA;
		}
	}
	
	public String toString() {
		return "networkCreator: " + networkCreator + ", networkName: " + networkName + ", requester: " + requester + ", action: " + action + ", newProteinA: " + Integer.toString(newProteinA) + ", newProteinB: " + Integer.toString(newProteinB);
	}
	
	public String simpleToString() {
		if (action.equals("insert") || action.equals("delete")) 
			return action + "(" + Integer.toString(newProteinA) + ", " + Integer.toString(newProteinB) + ")";
		return "update";
	}
	
	public String generateNetworkEdgesDMLStatement() {
		String action = this.action.toLowerCase();
		if (action.equals("insert")) {
			return "INSERT INTO NetworkEdges VALUES ('" + this.networkCreator + "','" + this.networkName + "'," +
					Integer.toString(this.newProteinA) + "," + Integer.toString(this.newProteinB) + ")";
		}
		if (action.equals("delete")) {
			return "DELETE FROM NetworkEdges " + 
					"WHERE network_creator = '" + this.networkCreator + "' " +
					"AND network_name = '" + this.networkName + "' " +
					"AND proteinA_id = " + Integer.toString(this.newProteinA) + 
					" AND proteinB_id = " + Integer.toString(this.newProteinB);
		}
		if (action.equals("update")) {
			return "UPDATE";
		}
		return "";
	}
	
	public String generateContributionDMLStatement(String status, String message) {
//		if (this.oldProteinA == 0 && this.oldProteinB != 0) {
//			return "INSERT INTO Contribution VALUES ('" + this.requester + "','" + this.networkCreator + "','" + this.networkName + "',CURRENT_TIMESTAMP,'" + status + "','" + message + "','" + this.action +"'," + Integer.toString(newProteinA) + "," + Integer.toString(newProteinB) + ",null," + Integer.toString(oldProteinB) + ")";
//		}
//		if (this.oldProteinA != 0 && this.oldProteinB == 0) {
//			return "INSERT INTO Contribution VALUES ('" + this.requester + "','" + this.networkCreator + "','" + this.networkName + "',CURRENT_TIMESTAMP,'" + status + "','" + message + "','" + this.action +"'," + Integer.toString(newProteinA) + "," + Integer.toString(newProteinB) + "," + Integer.toString(oldProteinA) + ",null)";
//		}
//		if (this.oldProteinA == 0 && this.oldProteinB == 0) {
//			return "INSERT INTO Contribution VALUES ('" + this.requester + "','" + this.networkCreator + "','" + this.networkName + "',CURRENT_TIMESTAMP,'" + status + "','" + message + "','" + this.action +"'," + Integer.toString(newProteinA) + "," + Integer.toString(newProteinB) + ",null,null)";
//		}
		return "INSERT INTO Contribution VALUES ('" + this.requester + "','" + this.networkCreator + "','" + this.networkName + "',CURRENT_TIMESTAMP,'" + status + "','" + message + "','" + this.action +"'," + Integer.toString(newProteinA) + "," + Integer.toString(newProteinB) + "," + Integer.toString(oldProteinA) + "," + Integer.toString(oldProteinB) + ")";   
	}
}
