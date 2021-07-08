package partymain;

import enums.Type;
import menusAndDekos.Deko;
import menusAndDekos.PartyItem;

/**
 * Represents a party plan consisting of: number of guests (adult and children)
 * menus for adults and children (one or both) and a decoration for the party
 * 
 * @author unaga
 *
 */
public class Plan {
	int numberOfAdultGuests;
	int numberOfChildGuests;
	PartyItem adultsMenu, kidsMenu;
	Deko deko;
	Type partyType;

	/*
	 * @return the partyType
	 */
	public Type getPartyType() {
		return partyType;
	}

	/*
	 * @param partyType the partyType to set
	 */
	public void setPartyType(Type partyType) {
		this.partyType = partyType;
	}

	/*
	 * @return the numberOfAdultGuests
	 */
	public int getNumberOfAdultGuests() {
		return numberOfAdultGuests;
	}

	/*
	 * @param numberOfAdultGuests the numberOfAdultGuests to set
	 */
	public void setNumberOfAdultGuests(int numberOfAdultGuests) {
		this.numberOfAdultGuests = numberOfAdultGuests;
	}

	/*
	 * @return the numberOfChildGuests
	 */
	public int getNumberOfChildGuests() {
		return numberOfChildGuests;
	}

	/*
	 * @param numberOfChildGuests the numberOfChildGuests to set
	 */
	public void setNumberOfChildGuests(int numberOfChildGuests) {
		this.numberOfChildGuests = numberOfChildGuests;
	}

	/*
	 * @return the adultsMenu
	 */
	public PartyItem getAdultsMenu() {
		return adultsMenu;
	}

	/*
	 * @param adultsMenu the adultsMenu to set
	 */
	public void setAdultsMenu(PartyItem adultsMenu) {
		this.adultsMenu = adultsMenu;
	}

	/*
	 * @return the kidsMenu
	 */
	public PartyItem getKidsMenu() {
		return kidsMenu;
	}

	/*
	 * @param kidsMenu the kidsMenu to set
	 */
	public void setKidsMenu(PartyItem kidsMenu) {
		this.kidsMenu = kidsMenu;
	}

	/*
	 * @return the deko
	 */
	public Deko getDeko() {
		return deko;
	}

	/*
	 * @param deko the deko to set
	 */
	public void setDeko(Deko deko) {
		this.deko = deko;
	}

}
