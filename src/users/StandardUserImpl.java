package users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.Type;
import menusAndDekos.Deko;
import menusAndDekos.Menu;
import menusAndDekos.PartyItem;

import static util.ConsoleUtil.out;
import static util.ConsoleUtil.getIntInput;

public class StandardUserImpl implements StandardUser {

	@Override
	public Deko choseDeko(List<Deko> decorations, Type type) {
		if (decorations.isEmpty()) {
			out("List of decorations is empty, please contact Admin.");
			return null;
		}

		List<Deko> filteredList = new ArrayList<Deko>();

		if (Type.ADULT.equals(type)) {
			filteredList = decorations.stream().filter(deco -> Type.ADULT.equals(deco.getType()))
					.collect(Collectors.toList());
		} else {
			filteredList = decorations.stream().filter(deco -> Type.CHILD.equals(deco.getType()))
					.collect(Collectors.toList());
		}

		out("Please choose a decoration:\n");

		for (int i = 1; i <= filteredList.size(); i++) {
			Deko deko = filteredList.get(i - 1);
			out(i + ". " + deko.getName() + ", " + deko.getPrice());
		}

		return filteredList.get(getIntInput() - 1);
	}

	@Override
	public PartyItem choseMenu(List<Menu> menus, Type type) {
		if (menus.isEmpty()) {
			out("List of menus is empty, please contact Admin.");
			return null;
		}

		List<Menu> filteredList = new ArrayList<Menu>();
		boolean isAdult = Type.ADULT.equals(type);

		if (isAdult) {
			filteredList = menus.stream().filter(menu -> Type.ADULT.equals(menu.getType()))
					.collect(Collectors.toList());
			out("Please choose adult menu:");
		} else {
			filteredList = menus.stream().filter(menu -> Type.CHILD.equals(menu.getType()))
					.collect(Collectors.toList());
			out("Please choose child menu:");
		}

		for (int i = 1; i <= filteredList.size(); i++) {
			PartyItem menu = filteredList.get(i - 1);
			out(i + ". " + menu.getName() + ", " + menu.getPrice());
		}

		return filteredList.get(getIntInput() - 1);
	}

	@Override
	public int getNumberOfAdultGuests() {
		out("Please enter the number of adult guests: ");
		out("(0 if not applicable)");
		return getIntInput();
	}

	@Override
	public int getNumberOfChildrenGuests() {
		out("Please enter the number of children: ");
		out("(0 if not applicable)");
		return getIntInput();
	}

}
