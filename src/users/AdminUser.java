package users;

import static util.ConsoleUtil.out;
import static util.ConsoleUtil.getStringInput;
import static util.ConsoleUtil.getIntInput;

import java.util.List;

import enums.Type;
import menusAndDekos.Deko;
import menusAndDekos.Menu;
import menusAndDekos.PartyItem;

public class AdminUser {

	public AdminUser() {
		printAdminMenu();
	}

	private void printAdminMenu() {
		out("Please choose what would you like to edit?");
		out("1. Decorations");
		out("2. Menus");
		out("3. Back");
	}

	/*
	 * Prints available decorations to standard output (console)
	 * 
	 * @param decorations
	 */
	private void printDecorations(List<Deko> decorations) {
		if (decorations.size() == 0) {
			out("No decorations available.");
		} else {
			for (int i = 1; i <= decorations.size(); i++) {
				Deko deko = decorations.get(i - 1);
				out(i + ". " + deko.getName() + ", " + deko.getPrice());
			}
		}
	}

	/*
	 * Adds decoration to the list of decorations
	 * 
	 * @param decorations - list to add to
	 * 
	 * @param decoration - decoration to be added
	 */
	private void addNewDecoration(List<Deko> decorations, final Deko decoration) {
		decorations.add(decoration);
		out("Decoration " + decoration.getName() + " added to the list.");
	}

	/*
	 * Edits a decoration from the list
	 * 
	 * @param decorations - list of decorations
	 * 
	 * @param index - index of a decoration to edit
	 * 
	 * @return - 1 if successful, -1 if failure
	 */
	private int editDecoration(List<Deko> decorations, int index) {
		if (decorations.size() < index) {
			out("Wrong input!");
			return -1;
		}
		Deko dekoToEdit = decorations.get(index - 1);

		out("Please enter new description: ");
		String name = getStringInput();
		dekoToEdit.setName(name);

		out("Please enter new price: ");
		int price = getIntInput();
		dekoToEdit.setPrice(price);

		return 1;
	}

	/*
	 * Adds a menu to the list of menus
	 * 
	 * @param menues - a list of menus
	 * 
	 * @param menu - a menu to add
	 */
	private void addNewMenu(List<Menu> menues, final Menu menu) {
		menues.add(menu);
		out("Menu  " + menu.getName() + " added to the list.");
	}

	/*
	 * Edits a menu from the list
	 * 
	 * @param menues - list of menus
	 * 
	 * @param index - index of the menu to edit
	 */
	private void editMenu(List<Menu> menues, int index) {

		if (menues.isEmpty() || menues.size() < index) {
			out("Wrong input!");
		} else {
			PartyItem menuToEdit = menues.get(index - 1);

			out("Please enter new description: ");
			String name = getStringInput();
			menuToEdit.setName(name);

			out("Please enter new price: ");
			int price = getIntInput();
			menuToEdit.setPrice(price);
		}
	}

	/*
	 * Prints a menu to edit either decorations or menus to console
	 * 
	 * @param option - what to edit (decorations or menus)
	 */
	public void printEditMenu(final String option) {
		out("1. Add new " + option);
		out("2. Edit existing " + option);
		out("3. Remove existing " + option);
		out("4. Back");
	}

	/*
	 * Removes a decoration from the list
	 * 
	 * @param decorations - a list of decorations
	 * 
	 * @param index - index of a decoration to delete
	 * 
	 * @return 1 when success, -1 when failure
	 */
	public int removeDecoration(List<Deko> decorations, int index) {
		if (decorations.size() < index) {
			out("Wrong input!");
			return -1;
		}
		decorations.remove(index - 1);
		out("Decoration removed.");

		return 1;
	}

	/*
	 * Allows admin user to add a new decoration to the list Required input in Type,
	 * Description, Price format (e.g. Adult, Deco1, 50) Repeats recursively until
	 * the user provides correct input
	 * 
	 * @param decorations - list to which a decoration should be added
	 */
	public void handleDecoCreation(List<Deko> decorations) {
		out("Please enter decoration type (adult or child), description and price: ");
		String decorationNameAndPrice = getStringInput();

		// must be in format Name, Price
		boolean matches = decorationNameAndPrice.matches("\\w+, \\w*, \\w+");

		if (matches) {
			String[] split = decorationNameAndPrice.split(", ");
			Deko deko = new Deko();
			deko.setType(Type.valueOf(split[0].toUpperCase()));
			deko.setName(split[1]);
			deko.setPrice(Integer.parseInt(split[2]));
			addNewDecoration(decorations, deko);
		} else {
			out("Input must be in format Type, Description, Price (e.g. Adult, Baloons, 50). Please try again.");
			handleDecoCreation(decorations);
		}
	}

	/*
	 * Provides the option to edit an existing decoration on a list User is
	 * presented with a list and needs to chose which decoration to edit.
	 * 
	 * @param decorations - a list of available decorations
	 */
	public void handleDecoEdit(List<Deko> decorations) {
		if (decorations.isEmpty()) {
			out("No decorations available.");
		} else {
			out("Which decoration do you want to change:");
			printDecorations(decorations);

			int decoToEdit = getIntInput();

			int editDecoration = editDecoration(decorations, decoToEdit);

			if (editDecoration == -1) {
				handleDecoEdit(decorations);
			}
		}
	}

	/*
	 * Enables removal of decoration from a list. User is presented a list of
	 * decorations and needs to chose one to be deleted.
	 * 
	 * @param decorations - a list of decorations from which to delete
	 */
	public void handleDecoRemoval(List<Deko> decorations) {
		if (decorations.isEmpty()) {
			out("No decorations available.");
		} else {
			out("Which decoration do you want to remove:");
			printDecorations(decorations);

			int decoToRemove = getIntInput();

			int removeDecoration = removeDecoration(decorations, decoToRemove);

			if (removeDecoration == -1) {
				handleDecoRemoval(decorations);
			}
		}
	}

	/*
	 * Prints available menus from the list to standard output (console)
	 * 
	 * @param menus - a list of menus to print
	 */
	public void printMenus(List<Menu> menus) {
		if (menus.size() == 0) {
			out("No menues available.");
		} else {
			for (int i = 1; i <= menus.size(); i++) {
				PartyItem menu = menus.get(i - 1);
				out(i + ". " + menu.getName() + ", " + menu.getPrice());
			}
		}
	}

	/*
	 * Removes a menu froma a list of menus
	 * 
	 * @param menus - list of menus
	 * 
	 * @param index - an index of the menu to be removed
	 * 
	 * @return - 1 if success, -1 if fail
	 */
	public int removeMenu(List<Menu> menus, int index) {
		if (menus.size() < index) {
			out("Wrong input!");
			return -1;
		}
		menus.remove(index - 1);
		out("Menu removed.");

		return 1;
	}

	/*
	 * Allows admin user to add a new menu to the list Required input in Type,
	 * Description, Price format (e.g. Adult, Menu1, 50) Repeats recursively until
	 * the user provides correct input
	 * 
	 * @param menus - list to which a menu should be added
	 */
	public void handleMenuCreation(List<Menu> menus) {
		out("Please enter menu type (adult or child), description and price: ");
		String menuNameAndPrice = getStringInput();

		// must be in format Name, Price
		boolean matches = menuNameAndPrice.matches("\\w+, \\w*, \\w+");

		if (matches) {
			String[] split = menuNameAndPrice.split(", ");
			Menu menu = new Menu();
			menu.setType(Type.valueOf(split[0].toUpperCase()));
			menu.setName(split[1]);
			menu.setPrice(Integer.parseInt(split[2]));
			addNewMenu(menus, menu);
		} else {
			out("Input must be in format Type, Description, Price (e.g. Adult, Menu1, 50). Please try again.");
			handleMenuCreation(menus);
		}
	}

	/*
	 * Provides the option to edit an existing menu on a list User is presented with
	 * a list and needs to chose which menu to edit.
	 * 
	 * @param menus - a list of available menus
	 */
	public void handleMenuEdit(List<Menu> menus) {
		out("Which menu do you want to change:");
		printMenus(menus);

		int menuToEdit = getIntInput();

		editMenu(menus, menuToEdit);
	}

	/*
	 * Enables removal of a menu from a list. User is presented with a list of menus
	 * and needs to chose one to be deleted.
	 * 
	 * @param menus - a list of menus from which to delete
	 */
	public void handleMenuRemoval(List<Menu> menus) {
		if (menus.isEmpty()) {
			out("No decorations available.");
		} else {
			out("Which decoration do you want to remove:");
			printMenus(menus);

			int menuToRemove = getIntInput();

			int removeMenu = removeMenu(menus, menuToRemove);

			if (removeMenu == -1) {
				handleMenuRemoval(menus);
			}
		}
	}

}
