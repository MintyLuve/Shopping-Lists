public class Main {
    public static void main(String args[]){
        // Testing Singly Linked
        SinglyLinkedList<Integer> singly = new SinglyLinkedList<Integer>();
	    for (int i=10; i>= 0; i--){
	        singly.insertBeginning(i);
	    }
	    System.out.print("Singly List:  ");
	    singly.print();

        // Testing Doubly Linked
	    DoublyLinkedList<Integer> doubly = new DoublyLinkedList<Integer>();
	    for (int i=0; i<= 10; i++){
	        doubly.insertBeginning(i);
	    }
	    System.out.print("Doubly List:  ");
	    doubly.printForward();
    }
}
