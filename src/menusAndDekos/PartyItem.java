package menusAndDekos;

import enums.Type;

/**
 * Represents a component of a party such as menu, decoration or something else
 * needed to organize a party Has name/description, price and can be either of
 * adult or child type
 * 
 * @author unaga
 *
 */
public abstract class PartyItem {
	private String name;
	private String price; // includes price and currency (50€)
	private Type type;

	public PartyItem() {
		super();
	}

	/*
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/*
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/*
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/*
	 * @return price of the menu
	 */
	public int getPrice() {
		String number = price.replaceAll("\\D++", ""); // remove non-digits (50€ to 50)
		return Integer.parseInt(number); // convert to int
	}

	public String getFormattedPrice() {
		return price;
	}

	/*
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = Integer.toString(price) + " €";

	}

	@Override
	public String toString() {
		return "PartyItem [name=" + name + ", price=" + price + ", type=" + type + "]";
	}

}