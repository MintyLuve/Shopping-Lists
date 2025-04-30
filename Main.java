import java.util.Scanner;

public class Main {
	public static ShoppingList list = new ShoppingList();
	public static ShoppingList purchased = new ShoppingList();

    public static void main(String args[]){
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
			list.insertSortedAlphabetically(item, price);
			sc.nextLine();
		}
		list.printAsShoppingList("Shopping List");

		System.out.println("Time to head to the store!\n" +
						   "Now that you are there, start by typing the first item you want to buy.\n" +
						   "Type !view at any time to see your list.\n" + 
						   "Type !stop at any time to stop buying items.\n" + 
						   "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		double total = 0;
		while (list.hasItems()) {
			total = buyItem(getInput(sc, "Enter item: "), total);
		}
		purchased.printAsShoppingList("You Purchased");
		String formattedTotal = String.format("%." + 2 + "f", total);
		System.out.println("Your total is: $"+ formattedTotal + "\nHave a good day~!");

    }

	private static String formatString(String input){
		int lastIndex = 0;
		String output = "";
		String word = "";
		if (input.length() == 0) {
			return output;
		}
		for (int i = 1; i< input.length()-2; i++) {
			if (input.substring(i, i+1).equals(" ") && !input.substring(i+1, i+2).equals(" ")) {
				word = input.substring(lastIndex, i);
				output += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " "; 
				lastIndex = i+1;
			}
		}
		word = input.substring(lastIndex);
		output += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase(); 
		return output;
	}

	private static String getInput(Scanner sc, String text){
		System.out.print(text);
		String input = sc.nextLine();
		if (input.equals("!view")){
			list.printAsShoppingList("Shopping List");
			input = getInput(sc, text);
		}
		if (input.equals("!stop")){
			return "!stop";
		}
		return formatString(input);
	}

	private static double buyItem(String item, double total){
		// If the item is not in the list
		if (item.equals("!stop")){
			list.deleteAllItemsInList();
			return total;
		}
		if (!list.isInList(item)){
			System.out.println("You don't need to buy "+item.toLowerCase()+".");
			return total;
		}
		double chance = Math.random();
		if (chance > 0.4){
			double price = list.extractPrice(item);
			total += price;
			purchased.insertSortedAlphabetically(item, price);
			String formattedTotal = String.format("%." + 2 + "f", total);
			System.out.println("Purchased "+item.toLowerCase()+" | Total: $" + formattedTotal);
		}
		else if (chance > 0.2){
			System.out.println(item+" is on sale for 15% off!");
			double price = list.extractPrice(item);
			double savings = price * 0.15;
			price -= savings;
			total += price;
			purchased.insertSortedAlphabetically(item +" (SALE)", price);
			String formattedTotal = String.format("%." + 2 + "f", total);
			String formattedSavings = String.format("%." + 2 + "f", savings);
			System.out.println("Purchased "+item.toLowerCase()+" on sale, saving $" + formattedSavings+ " | Total: $" + formattedTotal);
		}
		else {
			System.out.println(item+" is out of stock! You'll have to buy it next time.");
			list.extractPrice(item);
		}
		return total;
	}
}
