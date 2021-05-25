import java.io.Serializable;

public class MainMotion implements Serializable{
	//private String presentedBy;
	private String proposal;
	//private boolean seconded;
	//private boolean approved;
	
	/*
	 * 
	 */
	//constructors
	
	public MainMotion() {
		
	}
	
//	public MainMotion(String proposal, String memberName) {
//		this.presentedBy = memberName;
//		this.proposal = proposal;
//	}
	public MainMotion(String proposal) {
		this.proposal = proposal;
	}
	//accesors
	public String getProposal() {
		return this.proposal;
	}
	//mutators
	public void setProposal(String newProposal) {
		this.proposal = newProposal;
	}
	
	//tableMotion()
	
}
