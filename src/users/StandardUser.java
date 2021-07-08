package users;

import java.util.List;

import enums.Type;
import menusAndDekos.Deko;
import menusAndDekos.Menu;
import menusAndDekos.PartyItem;

/**
 * Defines a set of methods every standard user needs to implement
 * 
 * @author unaga
 *
 */
public interface StandardUser {

	/*
	 * Filters list of decorations based on type and returns the one selected by the
	 * user
	 * 
	 * @param decorations
	 * 
	 * @param type
	 * 
	 * @return selectedDecoration
	 */
	Deko choseDeko(List<Deko> decorations, Type type);

	/*
	 * Filters menus based on type and allows the user to select one menu
	 * 
	 * @param menues
	 * 
	 * @param type
	 * 
	 * @return selectedMenu
	 */
	PartyItem choseMenu(List<Menu> menues, Type type);

	/*
	 * Allows the user to input the number of adults
	 * 
	 * @return numberOfAdultGuests
	 */
	int getNumberOfAdultGuests();

	/*
	 * Allows the user to input number of children
	 * 
	 * @return numberOfChildGuests
	 */
	int getNumberOfChildrenGuests();

}
