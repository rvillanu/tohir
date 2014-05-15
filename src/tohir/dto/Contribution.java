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
		this.newProteinA = newProteinA;
		this.newProteinB = newProteinB;
	}
	
	// Contribution is an UPDATE to an existing network
	public Contribution(String networkCreator, String networkName, String requester, String action, int newProteinA, int newProteinB, int oldProteinA, int oldProteinB) {
		this.networkCreator = networkCreator;
		this.networkName = networkName;
		this.requester = requester;
		this.action = action;
		this.newProteinA = newProteinA;
		this.newProteinB = newProteinB;
		this.oldProteinA = oldProteinA;
		this.oldProteinB = oldProteinB;
	}
	
	public String toString() {
		return "networkCreator: " + networkCreator + ", networkName: " + networkName + ", requester: " + requester + ", action: " + action + ", newProteinA: " + Integer.toString(newProteinA) + ", newProteinB: " + Integer.toString(newProteinB);
	}
}
