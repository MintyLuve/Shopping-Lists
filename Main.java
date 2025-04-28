import java.util.Scanner;

public class Main {
    public static void main(String args[]){
		ShoppingList list = new ShoppingList();
		Scanner sc = new Scanner(System.in);

		System.out.println("Go you to open your fridge, and realize it's basically empty! You need to go grocery shopping.\n" +
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
		list.printAsShoppingList();

		System.out.println("Time to head to the store!\n" +
						   "Start by typing the first item.\n" +
						   "Type !view at any time to see your list\n" + 
						   "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(getInput(sc, list, "Enter item: "));
    }

	private static String formatString(String input){
		int lastIndex = 0;
		String output = "";
		String word = "";
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

	private static String getInput(Scanner sc, ShoppingList list, String text){
		System.out.println(text);
		String input = sc.nextLine();
		if (input.equals("!view")){
			list.printAsShoppingList();
			input = getInput(sc, list, text);
		}
		return formatString(input);
	}
}
