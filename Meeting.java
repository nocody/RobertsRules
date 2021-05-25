/**
 * @author Nick Cody
 * 
 * @section CSCI 150 - 01 
 * 
 * @date 11/25/2019
 * 
 * @assignment Project Part C
 *
 * @description This program allows users to run a meeting based on the Procedures outlined in Robert's Rules of Order.
 * 				Users are given the option to modify the meeting agenda or initiate the meeting. Minutes are automatically recorded, and will be read next time a meeting is initiated.
 */

public class Meeting {

	public static void main(String[] args) {
		Agenda meeting = new Agenda();
		Agenda minutes = new Agenda();
		minutes = UserServices.getMinutes();
//		meeting.setMembership(minutes.getMembership());
		meeting.setOldBusiness(minutes.getTabledMotions());
		meeting.setReports(minutes.getReports());
		UserServices.printWelcome(meeting);
		UserServices.startMenu(meeting, minutes);
		}//close main
		
}//close class
