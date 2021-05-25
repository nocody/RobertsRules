import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
//import java.util.Scanner;
public class Agenda implements Serializable {
//	Scanner in = new Scanner(System.in);
	private String organizationName;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	//private String presidingOfficer;
	private DoublyLinkedList<String> membership;
	private DoublyLinkedList<String> membersPresent;//should this be linked list or array list? Member objects or name strings?
	private DoublyLinkedList<String> reports; 
	//private boolean minutesApproved;
	public DoublyLinkedList<MainMotion> oldBusiness;
	public DoublyLinkedList<MainMotion> newBusiness;
	public DoublyLinkedList<MainMotion> BusinessItems;
	public DoublyLinkedList<MainMotion> tabledMotions;
	public DoublyLinkedList<MainMotion> passedMotions;
	public DoublyLinkedList<MainMotion> failedMotions;
	
	private boolean quorum;
	
	
	
	
	//constructors
	public Agenda() {
		this.organizationName = "";
		this.date = LocalDate.now();
		this.startTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
		this.endTime = null;
		//this.presidingOfficer = ;
		this.membership = new DoublyLinkedList<String>();
		this.membersPresent = new DoublyLinkedList<String>();
		this.reports = new DoublyLinkedList<String>();
		this.oldBusiness = new DoublyLinkedList<MainMotion>();
		this.newBusiness = new DoublyLinkedList<MainMotion>();
		this.tabledMotions = new DoublyLinkedList<MainMotion>();
		this.passedMotions = new DoublyLinkedList<MainMotion>();
		this.failedMotions = new DoublyLinkedList<MainMotion>();
		
	}
	//accessors
	
	public LocalDate getDate() {
		return this.date;
	}
	
	 public LocalTime getStartTime() {
		 return this.startTime;
	 }
	 
	 public LocalTime getEndTime() {
		 return this.endTime;
	 }
	 
	 public DoublyLinkedList<String> getMembersPresent(){
		 return this.membersPresent;
	 }
	 
	 public DoublyLinkedList<String> getReports(){
		 return this.reports;
	 }
	 public DoublyLinkedList<MainMotion> getOldBusiness(){
		 return this.oldBusiness;
	 }
	 public DoublyLinkedList<MainMotion> getNewBusiness(){
		 return this.newBusiness;
	 }
	 
	 public DoublyLinkedList<String> getMembership(){
			return this.membership;
		}
	 public String getOrganizationName() {
		 return this.organizationName;
	 }
	 
	 public boolean getQuorum() {
		 return this.quorum;
	 }
	 
	 public DoublyLinkedList<MainMotion> getTabledMotions(){
		 return this.tabledMotions;
	 }
	 public DoublyLinkedList<MainMotion> getPassedMotions(){
		 return this.passedMotions;
	 }
	 public DoublyLinkedList<MainMotion> getFailedMotions(){
		 return this.failedMotions;
	 }
	//mutators
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setStartTime(LocalTime time) {
		 this.startTime = time;
	 }
	
	public void setEndTime(LocalTime time) {
		 this.endTime = time;
	 }
	
	public void setQuorum(boolean q) {
		this.quorum = q;
	}
	public void addNewBusiness(MainMotion item) {
		this.newBusiness.addLast(item);
	}
	
	public void setOldBusiness(DoublyLinkedList<MainMotion> items) {
		this.oldBusiness = items;
	}
	
	public void addMembersPresent(String member) {
		this.membersPresent.addLast(member);
	}
	public void tableMotion(MainMotion item) {
		this.tabledMotions.addLast(item);
	}
	public void passMotion(MainMotion item) {
		this.passedMotions.addLast(item);
	}
	public void failMotion(MainMotion item) {
		this.failedMotions.addLast(item);
	}
	
	public void setOrganizationName(String name) {
		this.organizationName = name;
	}
	public void setMembership(DoublyLinkedList<String> mems){
		this.membership = mems;
	}
	public void setReports(DoublyLinkedList<String> reps) {
		this.reports = reps;
	}
	public void addReport(String report) {
		this.reports.addLast(report);
	}
	//printAgenda
	
	 public void printAgendaItems() {
		 UserServices.printHeader("Agenda Items");
		 readReports();
		 System.out.println("Ongoing Business:");
		 printMotions(this.oldBusiness);
		 System.out.println("New Business:");
		 printMotions(this.newBusiness);
		 readReports();
	 }
	

 
	

	

	
	/**
	 * (add later)
	 * allows editing of minutes file
	 */
	//amendMinutes
	
	/**
	 * executes:
	 * 	readMinutes
	 * 	while(!minutesApproved)
	 * 		ask for  corrections to minutes
	 * 			yes:
	 * 			execute amendMinutes
	 * 			no:
	 * 			set minutesApproved to true
	 */
	//considerMinutes
	
	/**
	 * while(reports.next != null)
	 * 	print next report
	 */
	//readReports
	public void readReports() {
		if(!this.reports.isEmpty()) {
		UserServices.printHeader("Officer Reports");
		this.reports.iterateForward();
		}else {
			System.out.println("No reports on today's agenda.");
		}
	}//close readReports
	
	
	/**
	 * (add later)
	 */
	//debateMotion
	public void debateMotion(MainMotion item) {//item
		
	}
	
	/**
	 * while furtherDiscussion == true
	 * executes debateMotion
	 * 
	 * gets yays and nays
	 * vote
	 * 	if true motion approved
	 * 	if false motion dismissed
	 */
	//considerMotion
	public void considerMotion(MainMotion item) {//add options to table/ postpone
		System.out.println("Motion to consider: " + item.getProposal());
//		boolean furtherDiscussion = true;
//				while(furtherDiscussion) {
//			debateMotion(item);
//		}
		if(UserServices.checkToVote()) {
			if(UserServices.vote()) {
				System.out.println("Motion passes");
				this.passMotion(item);
			} else {
				System.out.println("Motion fails");
				this.failMotion(item);
			}
		} else {
			System.out.println("Motion tabled");
			this.tableMotion(item);
		}
		
	}// close considerMotion
	/**
	 * constructs motion object and adds to newBusiness
	 * params - String submittingMember, String proposal
	 */
	//submitMotion
	
	public void submitMotion(String proposal) {
		
			MainMotion motion = new MainMotion(proposal);
			
			considerMotion(motion);
		
		
	}
//	public void submitMotion(String proposal, String memberName) {
//		
//		MainMotion motion = new MainMotion(proposal, memberName);
//		
//		considerMotion(motion);
//	
//	
//}
	
	/**
	 * iterates through oldBusiness
	 * 	executes considerMotion for each
	 */
	//considerOldBusiness
	public void considerOldBusiness() {
		Node<MainMotion> tmp = this.oldBusiness.head;
		while(tmp != null) {
			considerMotion(tmp.element);
			tmp = tmp.next;
		}	
	}//close considerOldBusiness
	
	/**
	 * while newBusiness.next != null
	 * iterates through newBusiness
	 * 	executes considerMotion for each
	 * 
	 * prompt for new business items
	 * if yes submit motion
	 */
	//considerNewBusiness
	public void considerNewBusiness() {
		Node<MainMotion> tmp = this.newBusiness.head;
		while(tmp != null) {
			considerMotion(tmp.element);
			tmp = tmp.next;
		}
		
	}//close considerNewBusiness
	
	public void printMotions(DoublyLinkedList<MainMotion> motions) {
		Node<MainMotion> tmp = motions.head;
		while(tmp != null) {
			System.out.println("Motion to consider: " + tmp.element.getProposal());;
			tmp = tmp.next;
		}
	}
	
}
