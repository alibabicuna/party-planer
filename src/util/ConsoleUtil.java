package util;

import java.util.Scanner;

/**
 * Utility methods for working with the console
 * (input, output)
 * 
 * @author unaga
 *
 */

public class ConsoleUtil {

	public static void out(final String text) {
		System.out.println(text);
	}
	
	/**
	 * Used when user has to enter a number
	 * (e.g. choose menu option)
	 * @return a number entered by the user
	 */
	public static int getIntInput() {
		Scanner scanner = new Scanner(System.in);
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			out("Wrong input. Please try agin.");
			getIntInput();
		}
		return 0;
	}
	
	/**
	 * Used for getting a string input from the user
	 * (e.g. when editing decorations or menus)
	 * @return entered string
	 */
	public static String getStringInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
