package partymain;

import static util.ConsoleUtil.getIntInput;
import static util.ConsoleUtil.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.Type;
import users.AdminUser;
import users.StandardUser;
import menusAndDekos.Deko;
import menusAndDekos.Menu;

/**
 * A main class which holds list of decorations and menus for party planning and
 * a party Plan which is created by choosing options from those lists
 * 
 * @author unaga
 *
 */
public class PartyPlanner {
	Plan partyPlan = new Plan();
	List<Menu> menus = new ArrayList<Menu>();
	List<Deko> decorations = new ArrayList<Deko>();

	public PartyPlanner() {
		printWelcomeMessage();
	}

	private void printWelcomeMessage() {
		out("Welcome to Twinkel Party Planner.");
	}

	/*
	 * To be able to plan a party there have to exist menus and decorations of
	 * respective type (adult for adult party)
	 * 
	 * @return
	 */
	private boolean checkPrerequisitesForAdultParty() {
		List<Deko> adultDecorations = decorations.stream().filter(deco -> Type.ADULT.equals(deco.getType()))
				.collect(Collectors.toList());
		List<Menu> adultMenus = menus.stream().filter(menu -> Type.ADULT.equals(menu.getType()))
				.collect(Collectors.toList());

		if (adultDecorations.size() == 0) {
			out("There are no adult decorations available. Please contact admin.");
			return false;
		}

		if (adultMenus.size() == 0) {
			out("There are no adult menus available. Please contact admin.");
			return false;
		}

		return true;
	}

	/*
	 * To be able to plan a party there have to exist menus and decorations of
	 * respective type (child for children party)
	 * 
	 * @return
	 */
	private boolean checkPrerequisitesForChildParty() {
		List<Deko> childDecorations = decorations.stream().filter(deco -> Type.CHILD.equals(deco.getType()))
				.collect(Collectors.toList());
		List<Menu> childMenus = menus.stream().filter(menu -> Type.CHILD.equals(menu.getType()))
				.collect(Collectors.toList());

		if (childDecorations.size() == 0) {
			out("There are no child decorations available. Please contact admin.");
			return false;
		}

		if (childMenus.size() == 0) {
			out("There are no child menus available. Please contact admin.");
			return false;
		}

		return true;
	}

	/*
	 * Allows administrators to manage decorations (e.g. for event managers)
	 * 
	 * @param adminUser
	 */
	public void handleDecoAdministration(AdminUser adminUser) {
		adminUser.printEditMenu("decoration");

		int editOption = getIntInput();

		switch (editOption) {
		case 1:
			adminUser.handleDecoCreation(decorations);
			break;
		case 2:
			adminUser.handleDecoEdit(decorations);
			break;
		case 3:
			adminUser.handleDecoRemoval(decorations);
			break;
		case 4:
		default:
			break;
		}

	}

	/*
	 * Allows administrators to manage menus (e.g. for event managers)
	 * 
	 * @param adminUser
	 */
	public void handleMenuAdministration(AdminUser adminUser) {
		adminUser.printEditMenu("menu");

		int editOption = getIntInput();

		switch (editOption) {
		case 1:
			adminUser.handleMenuCreation(menus);
			break;
		case 2:
			adminUser.handleMenuEdit(menus);
			break;
		case 3:
			adminUser.handleMenuRemoval(menus);
			break;
		case 4:
		default:
			break;
		}

	}

	/*
	 * Allows standard users to select party type Based on this selection users have
	 * to chose corresponding menus and decorations
	 * 
	 * @return
	 */
	public int handlePartyTypeSelection() {
		out("Please select a party type:");
		out("1. Adult");
		out("2. Children");
		int partyType = getIntInput();

		if (partyType == 1 && checkPrerequisitesForAdultParty()) {
			partyPlan.setPartyType(Type.ADULT);
			return 1;
		} else if (partyType == 2 && checkPrerequisitesForChildParty()) {
			partyPlan.setPartyType(Type.CHILD);
			return 1;
		}

		return -1;
	}

	/*
	 * Allows standard user to select party decoration from list of available
	 * decorations which match the party type
	 * 
	 * @param user
	 */
	public void handleDecoSelection(StandardUser user) {
		Deko chosenDeko = user.choseDeko(decorations, partyPlan.getPartyType());
		partyPlan.setDeko(chosenDeko);
	}

	/*
	 * Allows standard user to enter number of guests for the party They can enter
	 * number of adults and / or children regardless of party type (adult party can
	 * have children guests too)
	 * 
	 * @param user
	 */
	public void handleGuestsEntry(StandardUser user) {
		out("Number of adults: ");
		out("(0 if none)");
		partyPlan.setNumberOfAdultGuests(getIntInput());
		out("Number of children: ");
		out("(0 if none)");
		partyPlan.setNumberOfChildGuests(getIntInput());
	}

	/*
	 * Based on number of guests entered, provides a user with available menus for
	 * adults and children to chose from
	 * 
	 * @param user
	 */
	public void handleMenuSelection(StandardUser user) {
		if (partyPlan.getNumberOfAdultGuests() > 0) {
			partyPlan.setAdultsMenu(user.choseMenu(menus, Type.ADULT));
		}
		if (partyPlan.getNumberOfChildGuests() > 0) {
			partyPlan.setKidsMenu(user.choseMenu(menus, Type.CHILD));
		}
	}

	/*
	 * Calculates party costs based on options in current party {@Plan}
	 * 
	 * @return
	 */
	public int calculatePartyCosts() {
		int adults = partyPlan.getNumberOfAdultGuests();
		int children = partyPlan.getNumberOfChildGuests();
		int adultMenuPrice = 0;
		int childMenuPrice = 0;

		if (adults > 0) {
			adultMenuPrice = partyPlan.getAdultsMenu().getPrice();
		}

		if (children > 0) {
			childMenuPrice = partyPlan.getKidsMenu().getPrice();
		}

		int dekoPrice = partyPlan.getDeko().getPrice();

		return (adults * adultMenuPrice) + (children * childMenuPrice) + dekoPrice;
	}
}
