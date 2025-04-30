import java.util.Scanner;

public class Main {
	// Creates empty shopping and purchasing lists
	public static ShoppingList list = new ShoppingList();
	public static ShoppingList purchased = new ShoppingList();

    public static void main(String args[]){
		/*  Creating the list */
		Scanner sc = new Scanner(System.in);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
						   "Go you to open your fridge, and realize it's basically empty! You need to go grocery shopping.\n" +
						   "You need to write down what you need to buy- and how much it's going to cost.\n" +
						   "Type !stop to stop creating your shopping list.\n" + 
						   "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		String item;
		double price;
		while (true){
			// Get item name from user
			System.out.print("Item Name: ");
			item = sc.nextLine();
			if (item.equals("!stop")) { break; }				
			item = formatString(item);

			// Get item price from user
			System.out.print("Item Price: ");
			while(true){
				try {
					price = sc.nextDouble();
					break;
				}
				catch(Exception e){
				   System.out.println("ERROR: Not a double;\nItem Price: ");
				   sc.next();
				}
			}
			// Insert the item into the list
			list.insertSortedAlphabetically(item, price);
			sc.nextLine();
		}
		list.printAsShoppingList("Shopping List");

		/*	At the store  */
		System.out.println("Time to head to the store!\n" +
						   "Now that you are there, start by typing the first item you want to buy.\n" +
						   "Type !view at any time to see your list.\n" + 
						   "Type !stop at any time to stop buying items.\n" + 
						   "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		double total = 0;
		// Iterate through list, calling buyItem
		while (list.hasItems()) {
			total = buyItem(getInput(sc, "Enter item: "), total);
		}
		// Print reciept
		purchased.printAsShoppingList("You Purchased");
		String formattedTotal = String.format("%." + 2 + "f", total);
		System.out.println("Your total is: $"+ formattedTotal + "\nHave a good day~!");

    }

	/**
	 * Formats a string to have first letter of each word capitalized.
	 * @param input The string you want to format
	 * @return The formatted string
	 */
	private static String formatString(String input){
		int lastIndex = 0;
		String output = "";
		String word = "";
		// If string is empty, return empty string
		if (input.length() == 0) {
			return output;
		}
		//For length of string
		for (int i = 1; i< input.length()-2; i++) {
			// If character at index i is a space and the next character is not a space
			if (input.substring(i, i+1).equals(" ") && !input.substring(i+1, i+2).equals(" ")) {
				// Gets the word and adds it's formatted version to the output
				word = input.substring(lastIndex, i);
				output += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " "; 
				lastIndex = i+1;
			}
		}
		// Capitalizes the first letter of the last word
		word = input.substring(lastIndex);
		output += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase(); 
		return output;
	}

	/**
	 * Gets input from the user while letting them use the specified functions.
	 * @param sc The scanner from main
	 * @param prompt What you want to prompt the user
	 * @return A formatted string of the user's input
	 */
	private static String getInput(Scanner sc, String prompt){
		// Get input
		System.out.print(prompt);
		String input = sc.nextLine();
		// If the user wants to view their shopping list
		if (input.equals("!view")){
			list.printAsShoppingList("Shopping List");
			input = getInput(sc, prompt);
		}
		// If the user wants to stop purchasing items
		if (input.equals("!stop")){
			return "!stop";
		}

		return formatString(input);
	}

	/**
	 * Has all possible outcomes when the user tries to buy the specified item. Updates both the purchased and the shopping list.
	 * @param item The name of the item the user wants to buy
	 * @param total The total amount of money the user has spent
	 * @return The updated total amount of money the user has spent
	 */
	private static double buyItem(String item, double total){
		// If the user wants to stop, delete all items in  list
		if (item.equals("!stop")){
			list.deleteAllItemsInList();
			return total;
		}
		// If the item is not in the list, tell the user that it is not in their list
		if (!list.isInList(item)){
			System.out.println("You don't need to buy "+item.toLowerCase()+".");
			return total;
		}
		// Chance of different outcomes
		double chance = Math.random();
		// 60% chance the user can buy the item
		if (chance > 0.4){
			// Extracts the price from the shopping list
			double price = list.extractPrice(item);
			total += price;
			// Adds purchased item to purchased list
			purchased.insertSortedAlphabetically(item, price);
			String formattedTotal = String.format("%." + 2 + "f", total);
			System.out.println("Purchased "+item.toLowerCase()+" | Total: $" + formattedTotal);
		}
		// 20% chance the item is 15% off
		else if (chance > 0.2){
			// Calculates sale price between 10% and 20%
			int percent = (int) (Math.random() * 10) + 10;
			System.out.println(item+" is on sale for "+percent+"% off!");
			double price = list.extractPrice(item);
			double savings = price * (percent/100.0);
			price -= savings;
			total += price;
			// Adds purcahsed item to list
			purchased.insertSortedAlphabetically(item +" (SALE)", price);
			String formattedTotal = String.format("%." + 2 + "f", total);
			String formattedSavings = String.format("%." + 2 + "f", savings);
			System.out.println("Purchased "+item.toLowerCase()+" on sale, saving $" + formattedSavings+ " | Total: $" + formattedTotal);
		}
		// 20% chance the item is out of stock
		else {
			// Removes the item from the shopping list
			System.out.println(item+" is out of stock! You'll have to buy it next time.");
			list.extractPrice(item);
		}
		return total;
	}
}
