import java.util.Scanner;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class UserServices {
	public static final String WELCOME = "Welcome to MinutesManager!";
	public static final String DESCRIPTION = "This program is a tool for running meetings based on the procedure's outlined in Robert's Rules of Order.";
	public static final String MENU1 = "M)odify Agenda\nI)nintiate Meeting";
	public static final String MENU2 = "P)rint Agenda\nA)dd business item\nS)ubmit report\nR)eturn to main menu";
	public static final String TRY = "T)ry again\nR)eturn to main menu.";
	public static final String PIN_PROMPT = "Please enter pin number: ";
	public static final String THIS_MEETING = "This meeting of ";
	public static final String CALL_ORDER = " is called to order.";
	public static final String ROLL_CALL = "Roll Call";
	public static final String INVALID = "Invalid input.";
	public static final String MENU_MOTION = "T)able motion.\nV)ote on motion.";
	public static  DoublyLinkedList<String> roster = new DoublyLinkedList<String>();
	private static final int PIN = 0000;
	
//	this.roster.addLast("Nick Cody");
	
	
	
	
	public static Scanner in = new Scanner(System.in);

	public static String choice;
	
	public static void printWelcome(Agenda a) {
		printHeader(WELCOME);
		System.out.println("");
		System.out.println(DESCRIPTION);
		System.out.println("");
		a.setOrganizationName("Numbers & Bytes");
		a.getMembership().addLast("Nick Cody");
		a.getMembership().addLast("Michael Jones");
		a.getMembership().addLast("Alessandro Molinas");
		a.getMembership().addLast("Henry Chen");
		a.getMembership().addLast("Tally Wright");
	}
	
	public static void startMenu(Agenda a, Agenda b) {
//		boolean modAgenda = false;
		boolean run = true;
		while(run) {
		System.out.println(MENU1);
		choice = in.nextLine();
			switch(choice) {
				case "m": 
				case "M":
					if(checkPin()) {adminMenu(a, b);}
					run = false;
					break;
				case "i":
				case "I":
					run = false;
					callToOrder(a,b);
					break;
			
				default:
					System.out.println(INVALID);
					break;
				}
			}
//			return modAgenda;
		}//close startMenu

	public static void adminMenu(Agenda a, Agenda b) {
		boolean run = true;
		while(run) {
				System.out.println(MENU2);
				choice = in.nextLine();
				switch(choice) {
					case "p":
					case "P":
						a.printAgendaItems();
						break;
					case "a":
					case "A":
						addAgendaItem(a);
						break;
					case "s":
					case "S":
						addAgendaReport(a);
						break;
					case "r":
					case "R":
						run = false;
						startMenu(a, b);
						break;
					default:
						System.out.println(INVALID);
						}
					}
			}// close admin menu
	
	public static void addAgendaItem(Agenda a) {
		System.out.println("Enter proposal: ");
		MainMotion item = new MainMotion(in.nextLine());
		a.addNewBusiness(item);
		System.out.print("Item added to agenda.\n");
	}
	public static void addAgendaReport(Agenda a) {
		System.out.println("Enter report: ");
		//MainMotion item = new MainMotion(in.nextLine());
		a.addReport(in.nextLine());
		System.out.print("Report added to agenda.\n");
	}
	
	public static boolean checkPin() {
		boolean matches = false;
		boolean run = true;
		int userPin = 9999;
		while(run && !matches) {
			System.out.println(PIN_PROMPT);
			userPin = in.nextInt();
			in.nextLine();
			matches = (PIN == userPin);
			if(matches) {
				System.out.println("Access granted.\n");
			} else {
				System.out.println("Incorrect pin.");
				System.out.println(TRY);
				choice = in.nextLine();
				switch(choice) {
					case "t":
					case "T":
						break;
					case "r":
					case "R":
						run = false;
						break;
					default:
						System.out.println(INVALID);
					}
				}
			}
			return matches;
		}//close checkPin
	
	
//	//meeting.printAgendaItems();
//	System.out.println("This program is a tool for running group meetings according to the parliamentary procedures outlined in Roberts Rules of Order.");
//	System.out.println("Many elements of this program are still under construction.");
//	System.out.println("M)odify Agenda");
//	System.out.println("I)nintiate Meeting");
//	choice = in.next().charAt(0);
//	switch(choice) {
//	case 'm':
//		break;
//	case 'i':
//		meeting.callToOrder();
//	}
	
	
	
	public static void printHeader(String name) {
		
		System.out.println("");
		System.out.println("----- " + name + " -----");
		
	}
	
	/**
	 * initiates meeting
	 * 
	 * executes:
	 * 	rollCall or checkQuorum
	 * 	readMinutes
	 * 	
	 */
	//callToOrder
	public static void callToOrder(Agenda a, Agenda m) {
		//roleCall();
		printHeader(THIS_MEETING + a.getOrganizationName() + CALL_ORDER);
		rollCall(a);
		a.setQuorum(checkQuorum(a.getMembership(), a.getMembersPresent()));  
		if(!a.getQuorum()) {
			System.out.println("Quorum not present.");
			adjourn(a);
		}
		System.out.println("Quorum present.");
		readMinutes(m);
		a.readReports();
		printHeader("Ongoing Business");
		if(a.oldBusiness.isEmpty()) {
			System.out.println("No ongoing business to consider.");
		} else {
			a.considerOldBusiness();
		}
		printHeader("New Business");
		if(a.newBusiness.isEmpty()) {
			
			System.out.println("No new business to consider.");
		} else {
			a.considerNewBusiness();
		}
		hearNewBusiness(a);
		
		adjourn(a);
		
		
		
	}//close callToOrder
	
	public static void rollCall( Agenda a) {
		printHeader(ROLL_CALL);
		String present;
		DoublyLinkedList<String> mems = a.getMembership();
		Node<String> tmp = mems.head;
		while(tmp != null) {
			System.out.println(tmp.element + " P)resent or A)bsent?");
			present = in.nextLine();
			switch(present) {
			case "p":
			case "P":
				a.addMembersPresent(tmp.element);
				tmp = tmp.next;
				break;
			case "a":
			case "A":
				tmp = tmp.next;
				break;
			default:
				System.out.println(INVALID);	
				break;
			}
//			tmp = tmp.next;
		}
		
	}
	
	public static boolean checkQuorum(DoublyLinkedList<String> totalMembers, DoublyLinkedList<String> presentMembers) {
		boolean quorumPresent = false;
		int present = presentMembers.size();
		int quorum = totalMembers.size()/2 + 1;
		if(present >= quorum) {
			quorumPresent = true;
			
		}
		return quorumPresent;
	}
	 
//	public static Agenda getMinutes() {
//		Agenda minutes;
//		//FileIOServices.openReadFile("minutes.txt");
//		minutes = FileIOServices.loadAgenda("minutes.bin");
//		System.out.println("Minutes loaded.");
//		return minutes;
//	}
	
	public static Agenda getMinutes() {
		Agenda minutes;
		//FileIOServices.openReadFile("minutes.txt");
		minutes = FileIOServices.loadAgenda();
		System.out.println("Minutes loaded.");
		return minutes;
	}
	
	public static void saveMinutes(Agenda a) {
		FileIOServices.openWriteFile("minutes.bin");
		FileIOServices.saveAgenda(a);
		System.out.println("Minutes saved.");
	}
	
	public static void adjourn(Agenda a) {
		in.close();
		System.out.println("Meeting Adjourned");
		a.setEndTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
		saveMinutes(a);
		System.exit(0);
//		FileIOServices.openWriteFile("minutes.txt");
//		FileIOServices.writeObject(a);
//		System.out.println("Minutes saved.");
		
	}
	
	public static void printAgendaItems(Agenda a) {
		 //printHeader("Agenda Items");
		 printHeader("Ongoing Business:");
		 a.oldBusiness.iterateForward();
		 printHeader("New Business:");
		 a.newBusiness.iterateForward();
	 }
	
	//check password
	public static void readMinutes(Agenda minutes) {
		printHeader("Minutes from " + minutes.getDate() + " meeting.");
		System.out.println("Meeting was called to order at " + minutes.getStartTime());
		System.out.println("Present members: ");
		minutes.getMembersPresent().iterateForward();
		System.out.println("");
		if(!minutes.getQuorum()) {
			System.out.println("Meeting adjourned early due to lack of quorum.");
			System.out.println("Meeting adjourned at: " + minutes.getEndTime());
			printHeader("end of minutes");
			System.out.println();
			return;
		}
		
		System.out.println("Reports presented: ");
		minutes.readReports();	
		System.out.println("");
		System.out.println("Motions passed: ");
		minutes.printMotions(minutes.getPassedMotions());
		System.out.println("");
		System.out.println("Motions failed: ");
		minutes.printMotions(minutes.getFailedMotions());
		System.out.println("");
		System.out.println("Motions tabled: ");
		minutes.printMotions(minutes.getTabledMotions());
		System.out.println("");
		System.out.println("Meeting adjourned at: " + minutes.getEndTime());
		printHeader("end of minutes");
		System.out.println();
		
		
	}
	
	public static boolean vote() {//fix for tie exception
		boolean pass = false;
		int yays = 0;
		
		
		while(yays == 0) {
		System.out.println("Enter votes for: ");
		yays += in.nextInt();
		in.nextLine();
		System.out.println("Enter votes against: ");
		yays -= in.nextInt();
		in.nextLine();
		pass = (yays > 0);
		if(yays == 0) {
			System.out.println("Tied results. Vote again.");
			}
		}
		
		return pass;
	}//close vote
	
	public static void hearNewBusiness(Agenda a) {
		boolean newMotions = true;
//		String prop;
//		String name;
		while(newMotions) {
			System.out.println("Does any member wish to submit a motion?(Y/N)");
			choice = in.nextLine();
			switch(choice) {
				case "y":
				case "Y":
					hearNewMotion(a);
					break;
				case "n":
				case "N":
					newMotions = false;
					break;
				
				default:
					System.out.println(INVALID);
					break;
			}
		}
	}//close hearNewBusiness
	
	public static void hearNewMotion(Agenda a) {
		String prop;
		String memberName;
		boolean run = true;
		System.out.println("Who is presenting motion?");
		memberName = in.nextLine();
		System.out.println("The chair recognizes " + memberName);
		//System.out.println(memberName);
		System.out.println("What does " + memberName + " propose?");
		prop = in.nextLine();
		System.out.println("");
		//System.out.println(prop);
		while(run) {
			System.out.println("Does any member second this motion?(Y/N)");
			choice = in.next();
			switch(choice) {
				case "y":
				case "Y":
					a.submitMotion(prop);
//					a.submitMotion(prop, memberName);
					run = false;
					break;
				case "n":
				case "N":
					run = false;
					break;
			
				default:
					System.out.println(INVALID);
					break;
			}
		}
		
	}//close hearNewMotion
	
	public static boolean checkToVote() {
		boolean vote = false;
		boolean run = true;
		while(run) {
			System.out.println(MENU_MOTION);
			choice = in.nextLine();
			switch(choice) {
			case "t":
			case "T":
				//vote = false;
				run = false;
				break;
			case "v":
			case "V":
				vote = true;
				run = false;
				break;
			default:
				System.out.println(INVALID);
				break;
			}
		}
		return vote;
	}
	

}
