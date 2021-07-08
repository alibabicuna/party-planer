
package partymain;

import enums.UserType;
import users.*;

import static util.ConsoleUtil.out;
import static util.ConsoleUtil.getIntInput;

/**
 * Creates a new instance of PartyPlanner and runs user type selection methods
 * in a loop until user exits the program by selecting an exit option
 * 
 * @author unaga
 * 
 */

public class Main {

	public static void main(String[] args) {
		PartyPlanner partyPlanner = new PartyPlanner();
		boolean running = true;

		while (running) {
			UserType userType = getUserType();

			if (UserType.ADMIN.equals(userType)) {
				handleAdminUser(partyPlanner);
			} else if (UserType.USER.equals(userType)) {
				handleStandardUser(partyPlanner);
			} else {
				running = false;
			}
		}
		out("----------------END-------------");

	}

	/*
	 * Returns ADMIN or STANDARD user depending on selection or null for all other
	 * inputs which is used to terminate program execution
	 * 
	 * @return UserType
	 */
	private static UserType getUserType() {
		out("Please choose user type:");
		out("1. Standard user - to plan a party");
		out("2. Admin - for managing menus and decorations");
		out("3. Exit program");

		int userType = getIntInput();
		if (userType == 1) {
			return UserType.USER;
		} else if (userType == 2) {
			return UserType.ADMIN;
		}

		return null;
	}

	/*
	 * Offers administrators decoration and menu administration options and back
	 * option to return to previous menu
	 * 
	 * @param partyPlanner
	 */
	private static void handleAdminUser(PartyPlanner partyPlanner) {
		AdminUser adminUser = new AdminUser();
		int option = getIntInput();

		switch (option) {
		case 1:
			partyPlanner.handleDecoAdministration(adminUser);
			break;
		case 2:
			partyPlanner.handleMenuAdministration(adminUser);
			break;
		case 3:
		default:
			break;
		}
	}

	/*
	 * Triggers a sequence of steps that standard users have to go through in order
	 * to plan a party and get total party price.
	 * 
	 * @param partyPlanner
	 */
	private static void handleStandardUser(PartyPlanner partyPlanner) {
		StandardUserImpl standardUser = new StandardUserImpl();

		int validSelection = partyPlanner.handlePartyTypeSelection();
		if (validSelection == 1) {
			partyPlanner.handleDecoSelection(standardUser);
			partyPlanner.handleGuestsEntry(standardUser);
			partyPlanner.handleMenuSelection(standardUser);

			out("Total party costs: " + partyPlanner.calculatePartyCosts() + " €");
			out("\n");
		} else {
			out("Unable to plan a party. Please contact admin.");
		}

	}

}
