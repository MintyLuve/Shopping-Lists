import java.util.Scanner;

public class Main {
    public static void main(String args[]){
		ShoppingList shoppingList = new ShoppingList();
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

			// Change words to be Upper Lower
			int lastIndex = 0;
			String newItem = "";
			String word = "";
			for (int i = 1; i< item.length()-1; i++) {
				if (item.substring(i, i+1).equals(" ") && !item.substring(i+1, i+2).equals(" ")) {
					word = item.substring(lastIndex, i);
					newItem += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " "; 
					lastIndex = i+1;
				}
			}
			word = item.substring(lastIndex);
			newItem += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase(); 					
			item = newItem;

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
			shoppingList.insertSortedAlphabetically(item, price);
			sc.nextLine();
		}

		shoppingList.printAsShoppingList();


    }
}
