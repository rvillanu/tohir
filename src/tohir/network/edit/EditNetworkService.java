package tohir.network.edit;

public class EditNetworkService {
	public String requestNetworkInsert() {
		// session username is NOT owner or collaborator, then this Insert must be a ContributionRequest
		return "OK";
	}
	
	public String NetworkInsert() {
		// if session username is owner or collaborator, then go ahead and just insert 
		// else call requestNetworkInsert in here
		return "OK";
	}
	
	public String requestNetworkDelete() {
		return "OK";
	}
	
	public String NetworkDelete() {
		return "OK";
	}
	
}
