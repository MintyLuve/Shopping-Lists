/* CITATION: This code was created based off of auditing the course
   Introduction to Java Programming: Fundamental Data Structures and Algorithms on EdX
   https://learning.edx.org/course/course-v1:UC3Mx+IT.1.3x+3T2024/home  */


/**   A singly linked list that can: <ul>
 *   <li> Insert from the beginning, end, and sorted </li>
 *   <li> Extract from the beginning or the end </li>
 *   <li> Delete the first occurrence of an item </li>
 *   <li> Delete all occurrences of an item </li>
 *   <li> Print the entire list </li> </ul>   
 */
public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;
    
    /**
     * A singly linked list that can: <ul>
     *   <li> Insert from the beginning, end, and sorted </li>
     *   <li> Extract from the beginning or the end </li>
     *   <li> Delete the first occurrence of an item </li>
     *   <li> Delete all occurrences of an item </li>
     *   <li> Print the entire list </li> </ul>   
     */
    public SinglyLinkedList(){
        // Creates the head and the tail
	    this.head = null;
	    this.tail = null;
    }
    
    /**
     * This function adds a new item, and inserts it before the head.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertBeginning(String item, double price){
	    Node<E> newNode = new Node<E>(item, price);
        // head (new node) --> old head
	    newNode.setNext(head);
	    head = newNode;
        // if there is no tail sets it to new node
	    if (tail == null){
	        tail = newNode;
	    }
    }

    /**
     * This function adds a new item, and inserts it after the tail.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertEnd(String item, double price){
	    Node<E> newNode = new Node<E>(item, price);
        if(tail != null){
            // old tail --> tail (new node)
            tail.setNext(newNode);
            tail = newNode;
        }
        // if there is no tail it sets it to new node
        else {
            tail = newNode;
        }
        // if there is no head sets it to new node
	    if (head == null){
	        head = newNode;
	    }

    }

    /**
     * This function adds a new item, and inserts it in the list alphabetically.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertSortedAlphabetically(String item, double price){
        // Gets the new node
	    Node<E> newNode = new Node<E>(item, price);
        // if the list doesn't exist, or if the info is less then the first value
        if(head == null || item.compareTo(head.getItem()) < 0){
            // first (new node) --> old first
            newNode.setNext(head);
            head = newNode;
        } else {
            //current node to use to go through the list
            Node<E> currentNode = head;
            //while there is a next node in the list
            while (currentNode.getNext() != null){
                //if the info is greater then the current info, but smaller then the next info
                if (item.compareTo(currentNode.getItem()) > 0 && item.compareTo(currentNode.getNext().getItem()) <= 0){
                    // current node --> new node --> next node
                    newNode.setNext(currentNode.getNext());
                    currentNode.setNext(newNode);
                    break;
                } else{
                    // moves onto the next node in the list
                    currentNode = currentNode.getNext();
                }
            }  
            // if the new node was not smaller then the last node 
            if (currentNode.getNext() == null) {
                // becomes the last node
                // current --> new node
                currentNode.setNext(newNode);
            }
        }
    }
    
    /**
     * This function removes the first node from the list, and returns its name
     * @return The name of the first item
     */
    public String extractFirstItem(){
        String data = null;
        // If the head exists
        if (head != null) {
            // Return the info then move the head up one
            data = head.getItem();
            head = head.getNext();
        }
        return data;
    }

    /**
     * This function removes the last item from the list, and returns its name
     * @return The name of the last item
     */
    public String extractLastItem(){
	    String item = null;

        // if the tail exists
        if (tail != null) {
            // Return the data
            item = tail.getItem();
            // if there is only one node, it deletes it and returns data
            if (head == tail){
                head = null;
                tail = null;
                return item;
            }
            // Getting the second to last node
            Node<E> prev = head;
            while (prev.getNext().getNext() != null){
                prev = prev.getNext();
            }

            // Moves the tail back one
            tail = prev;
            tail.setNext(null);
        }
        return item;
    }

    /**
     * This function deletes the first occurrence of the inputted item.
     * @param item The name of the item you want deleted.
     * @return Whether or not the item was successfully deleted
     */
    public boolean deleteFirstOccurrenceOfItem(String item){
        boolean success = false;
        Node<E> currentNode = head;

        // while the current node is not the last one, and it didn't find the first occurrence
         while (currentNode.getNext() != null && !success){
            Node<E> nextNode = currentNode.getNext();
            // If the current node is the first one, and the current node is the first occurrence
            if(currentNode == head && currentNode.getItem() == item) {
                // removes the reference to the old head, and makes current the new head
                head = head.getNext();
                currentNode = head;
                // Successfully deletes the first one, ending the loop
                success = true;
            }
            // If the next node is the first occurrence
            else if (nextNode.getItem() == item){
                // Changes the current node to reference the next next node
                currentNode.setNext(nextNode.getNext());
                // Successfully deletes the first one, ending the loop
                success = true;
            }
            else{
                // Traverses the list
                currentNode = nextNode;
            }
        }
        // returns if the function worked or not
        return success;
    }

    /**
     * This function deletes all occurrences of the inputted item.
     * @param item The name of the item you want deleted.
     * @return How many times the item got deleted.
     */
    public int deleteAllOccurrencesOfItem(String item){
        int count = 0;
        Node<E> currentNode = head;

        // While the current node is not the last node
        while (currentNode.getNext() != null){
            Node<E> nextNode = currentNode.getNext();
            // If the current node is the first one, and the current node is the first occurrence
            if(currentNode == head && currentNode.getItem() == item) {
                // removes the reference to the old head, and makes current the new head
                head = head.getNext();
                currentNode = head;
                // increments the count of how many times it was deleted
                count++;
            }
            else if (nextNode.getItem() == item){
                // Changes the current node to reference the next next node
                currentNode.setNext(nextNode.getNext());
                // increments the count of how many times it was deleted
                count++;
            }
            else{
                // Traverses the list
                currentNode = nextNode;
            }
       }
       // Returns how many times the value got deleted
       return count;
    }


    /**
     * This function prints out the entire linked list.
     */
    public void print(){
	    Node<E> current = head;
	    String formattedPrice;
        System.out.print("[ ");
        // Traverses the list, printing out the current value
	    while (current != null && current.getNext() != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
	        System.out.print(current.getItem() + " = $"+ formattedPrice +", ");
	        current = current.getNext();
	    }
        formattedPrice = String.format("%." + 2 + "f", current.getPrice());
        System.out.print(current.getItem() + " = $"+ formattedPrice +"");
	    System.out.println(" ]");
    }
}
