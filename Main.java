public class Main {
    public static void main(String args[]){
        // Testing Singly Linked
        SinglyLinkedList<Integer> singly = new SinglyLinkedList<Integer>();
	    for (double i=100; i>= 0; i -= 10){
	        singly.insertSortedAlphabetically("Singly", i);
	    }

        // Testing Doubly Linked
	    DoublyLinkedList<Integer> doubly = new DoublyLinkedList<Integer>();
	    for (int i=0; i<= 10; i++){
	        singly.insertSortedAlphabetically("Doubly", i);
	    }

		
	    System.out.print("Singly List:  ");
	    singly.print();
	    System.out.print("Doubly List:  ");
	    doubly.printForward();

		System.out.println("Go you to open your fridge, and realize it's basically empty! You need to go grocery shopping.\n" +
							"You need to write down what you need to buy- and how much it's going to cost.");
    }
}
